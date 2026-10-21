package at.spengergasse.spengerbite.repository;

import at.spengergasse.spengerbite.model.customer.Customer;
import at.spengergasse.spengerbite.model.order.Order;
import at.spengergasse.spengerbite.model.restaurant.MenuItem;
import at.spengergasse.spengerbite.model.restaurant.Restaurant;
import at.spengergasse.spengerbite.model.shared.Address;
import at.spengergasse.spengerbite.model.shared.Contact;
import at.spengergasse.spengerbite.model.shared.CuisineType;
import at.spengergasse.spengerbite.model.shared.Email;
import at.spengergasse.spengerbite.model.shared.Money;
import at.spengergasse.spengerbite.model.shared.OpeningHours;
import at.spengergasse.spengerbite.model.shared.OrderStatus;
import at.spengergasse.spengerbite.model.shared.PasswordHash;
import at.spengergasse.spengerbite.model.shared.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;

    // Save and Retrieve of the whole graph: order, items via cascade, restaurant, customer, snapshots
    @Test
    public void findById_ShouldReturnOrderWithItems_WhenOrderIsSaved() {

        // Given
        Restaurant mario = restaurantRepository.save(createRestaurant("Pizza Mario"));
        MenuItem margherita = menuItemRepository.save(createMenuItem(mario, "Margherita", "9.90"));
        MenuItem diavola = menuItemRepository.save(createMenuItem(mario, "Diavola", "11.50"));
        Customer max = customerRepository.save(createCustomer("max@spengergasse.at"));

        Order order = new Order(mario, max, max.getEmail(), max.getContact(), createAddress());
        order.addItem(margherita, 2);
        order.addItem(diavola, 1);
        orderRepository.save(order); // cascade saves the two items
        entityManager.clear();

        // When
        Order loaded = orderRepository.findById(order.getId()).orElseThrow();

        // Then
        assertEquals(OrderStatus.SUBMITTED, loaded.getStatus());
        assertEquals(2, loaded.getItems().size());
        assertEquals("Pizza Mario", loaded.getRestaurant().getName());
        assertEquals(max.getId(), loaded.getCustomer().getId());
        assertEquals(new BigDecimal("9.90"), loaded.getItems().getFirst().getUnitPrice().amount());
        assertNull(loaded.getPayment());
    }

    // Guest order: the only nullable FK in the model
    @Test
    public void findByCustomerOrderByCreatedAtDesc_ShouldReturnOnlyCustomerOrders_WhenGuestOrdersExist() {

        // Given
        Restaurant mario = restaurantRepository.save(createRestaurant("Pizza Mario"));
        MenuItem margherita = menuItemRepository.save(createMenuItem(mario, "Margherita", "9.90"));
        Customer max = customerRepository.save(createCustomer("max@spengergasse.at"));

        Order mine = new Order(mario, max, max.getEmail(), max.getContact(), createAddress());
        mine.addItem(margherita, 1);
        orderRepository.save(mine);

        Order guest = new Order(mario, null, new Email("guest@example.com"),
            new Contact("Gast", "Unbekannt", null), createAddress());
        guest.addItem(margherita, 1);
        orderRepository.save(guest);
        entityManager.clear();

        // When
        List<Order> found = orderRepository.findByCustomerOrderByCreatedAtDesc(max);

        // Then
        assertEquals(1, found.size());
        assertEquals(mine.getId(), found.getFirst().getId());
    }


    // --- Private Setup Helpers ---

    private static Restaurant createRestaurant(String name) {
        return new Restaurant(
            name,
            CuisineType.ITALIAN,
            createAddress(),
            new OpeningHours(LocalTime.of(9, 0), LocalTime.of(22, 0)),
            "image.jpg");
    }

    private static MenuItem createMenuItem(Restaurant restaurant, String name, String price) {
        return new MenuItem(restaurant, name, "Tomato, mozzarella, basil",
            new Money(new BigDecimal(price), "EUR"), "image.jpg");
    }

    private static Customer createCustomer(String email) {
        return new Customer(
            new Email(email),
            new PasswordHash("$2a$10$hash"),
            new Contact("Max", "Mustermann", new Phone("0660 123 45 67")));
    }

    private static Address createAddress() {
        return new Address("Wien", "Spengergasse 20", "1050");
    }
}
