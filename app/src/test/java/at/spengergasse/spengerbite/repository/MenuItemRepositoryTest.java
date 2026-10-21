package at.spengergasse.spengerbite.repository;

import at.spengergasse.spengerbite.model.restaurant.MenuItem;
import at.spengergasse.spengerbite.model.restaurant.Restaurant;
import at.spengergasse.spengerbite.model.shared.Address;
import at.spengergasse.spengerbite.model.shared.CuisineType;
import at.spengergasse.spengerbite.model.shared.Money;
import at.spengergasse.spengerbite.model.shared.OpeningHours;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MenuItemRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private TestEntityManager entityManager;

    // 1:n uni and the embedded Money: the finder goes through the FK
    @Test
    public void findByRestaurant_ShouldReturnMenuItems_WhenMenuItemsAreSaved() {

        // Given
        Restaurant mario = restaurantRepository.save(createRestaurant("Pizza Mario"));
        menuItemRepository.save(createMenuItem(mario, "Margherita", "9.90"));
        menuItemRepository.save(createMenuItem(mario, "Diavola", "11.50"));
        entityManager.clear();

        // When
        List<MenuItem> menu = menuItemRepository.findByRestaurant(mario);

        // Then
        assertEquals(2, menu.size());
        assertEquals(new BigDecimal("9.90"), menu.getFirst().getPrice().amount());
    }

    // Compound unique index over restaurant_id and name
    @Test
    public void save_ShouldFail_WhenNameExistsInSameRestaurant() {

        // Given
        Restaurant mario = restaurantRepository.save(createRestaurant("Pizza Mario"));
        menuItemRepository.save(createMenuItem(mario, "Margherita", "9.90"));

        // When, Then
        assertThrows(DataIntegrityViolationException.class,
            () -> menuItemRepository.saveAndFlush(createMenuItem(mario, "Margherita", "10.90")));
    }

    // The same name in another restaurant is fine, the index is compound
    @Test
    public void save_ShouldSucceed_WhenSameNameInOtherRestaurant() {

        // Given
        Restaurant mario = restaurantRepository.save(createRestaurant("Pizza Mario"));
        Restaurant luigi = restaurantRepository.save(createRestaurant("Pizza Luigi"));
        menuItemRepository.save(createMenuItem(mario, "Margherita", "9.90"));

        // When
        MenuItem saved = menuItemRepository.saveAndFlush(createMenuItem(luigi, "Margherita", "8.90"));

        // Then
        assertNotNull(saved.getId());
    }


    // --- Private Setup Helpers ---

    private static Restaurant createRestaurant(String name) {
        return new Restaurant(
            name,
            CuisineType.ITALIAN,
            new Address("Wien", "Spengergasse 20", "1050"),
            new OpeningHours(LocalTime.of(9, 0), LocalTime.of(22, 0)),
            "image.jpg");
    }

    private static MenuItem createMenuItem(Restaurant restaurant, String name, String price) {
        return new MenuItem(restaurant, name, "Tomato, mozzarella, basil",
            new Money(new BigDecimal(price), "EUR"), "image.jpg");
    }
}
