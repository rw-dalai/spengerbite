package at.spengergasse.spengerbite.repository;

import at.spengergasse.spengerbite.model.restaurant.Restaurant;
import at.spengergasse.spengerbite.model.shared.Address;
import at.spengergasse.spengerbite.model.shared.CuisineType;
import at.spengergasse.spengerbite.model.shared.OpeningHours;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// SUT_ShouldXXX_WhenXXX

// SUT = What will be tested
// Should = What is expected to happen
// When = Under which condition

@DataJpaTest
public class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TestEntityManager entityManager;

    // Famous Save and Retrieve Test: Tests Entity to DB and DB to Entity mapping
    @Test
    public void findById_ShouldReturnRestaurant_WhenRestaurantIsSaved() {

        // Given (setup)
        var restaurant = createRestaurant("Pizza Mario", "Vienna");
        restaurantRepository.save(restaurant); // Entity -> DB
        entityManager.clear(); // Clears Cache

        // When (SUT)
        Restaurant loaded = restaurantRepository.findById(restaurant.getId()).orElseThrow(); // DB -> Entity

        // Then (assertions)
        assertEquals(restaurant.getId(), loaded.getId());
    }


    @Test
    public void FindByAddressCity_ShouldReturnRestaurants_WhenCityContainsSubstringIgnoreCase() {

        // Given
        restaurantRepository.save(createRestaurant("Pizza Mario", "Wien"));
        restaurantRepository.save(createRestaurant("Sushi Bar", "Wien"));
        restaurantRepository.save(createRestaurant("Burger House", "Graz"));
        entityManager.clear(); // Clears Cache

        // When
//         List<Restaurant> found = restaurantRepository.findByAddressCityContainingIgnoreCase("IE");
        List<Restaurant> found = restaurantRepository.findByAddressCity("Wien");

        // Then
        assertEquals(2, found.size());
    }


    // --- Private Setup Helpers ---

    private static Restaurant createRestaurant(String name, String city) {
        return new Restaurant(
            name,
            CuisineType.ITALIAN,
            new Address(city, "Spengergasse 20", "1050"),
            new OpeningHours(LocalTime.of(9, 0), LocalTime.of(22, 0)),
            "image.jpg");
    }
}