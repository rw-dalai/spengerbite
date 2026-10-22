package at.spengergasse.spengerbite.repository;

import at.spengergasse.spengerbite.model.customer.Customer;
import at.spengergasse.spengerbite.model.order.Order;
import at.spengergasse.spengerbite.model.payment.CashPayment;
import at.spengergasse.spengerbite.model.restaurant.MenuItem;
import at.spengergasse.spengerbite.model.restaurant.Restaurant;
import at.spengergasse.spengerbite.model.shared.Address;
import at.spengergasse.spengerbite.model.shared.Contact;
import at.spengergasse.spengerbite.model.shared.CuisineType;
import at.spengergasse.spengerbite.model.shared.Email;
import at.spengergasse.spengerbite.model.shared.Money;
import at.spengergasse.spengerbite.model.shared.OpeningHours;
import at.spengergasse.spengerbite.model.shared.PasswordHash;
import at.spengergasse.spengerbite.model.shared.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PaymentRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TestEntityManager entityManager;

    // Single table with discriminator and the inverse side of the 1:1, filled by Hibernate on load
    @Test
    public void findById_ShouldReturnCashPayment_WhenOrderIsPaidInCash() {

        // Given
        Order order = orderRepository.save(createOrder());
        paymentRepository.save(new CashPayment(order, new Money(new BigDecimal("20.00"), "EUR")));
        entityManager.clear();

        // When
        Order loaded = orderRepository.findById(order.getId()).orElseThrow();

        // Then
        assertInstanceOf(CashPayment.class, loaded.getPayment());
        assertEquals(new BigDecimal("20.00"), ((CashPayment) loaded.getPayment()).getChangeFor().amount());
    }

    // 1:1 means unique: a second payment for the same order fails in the database
    @Test
    public void save_ShouldFail_WhenOrderIsAlreadyPaid() {

        // Given
        Order order = orderRepository.save(createOrder());
        paymentRepository.save(new CashPayment(order, null));

        // When, Then
        assertThrows(DataIntegrityViolationException.class,
            () -> paymentRepository.saveAndFlush(new CashPayment(order, null)));
    }


    // --- Private Setup Helpers ---

    private Order createOrder() {
        Restaurant mario = restaurantRepository.save(new Restaurant(
            "Pizza Mario",
            CuisineType.ITALIAN,
            new Address("Wien", "Spengergasse 20", "1050"),
            new OpeningHours(LocalTime.of(9, 0), LocalTime.of(22, 0)),
            "image.jpg"));
        MenuItem margherita = menuItemRepository.save(new MenuItem(mario, "Margherita", "Tomato, mozzarella, basil",
            new Money(new BigDecimal("9.90"), "EUR"), "image.jpg"));
        Customer max = customerRepository.save(new Customer(
            new Email("max@spengergasse.at"),
            new PasswordHash("$2a$10$hash"),
            new Contact("Max", "Mustermann", new Phone("0660 123 45 67"))));

        Order order = new Order(mario, max, max.getEmail(), max.getContact(), new Address("Wien", "Spengergasse 20", "1050"));
        order.addItem(margherita, 1);
        return order;
    }
}
