package at.spengergasse.spengerbite.api.restaurant;

import at.spengergasse.spengerbite.model.restaurant.MenuItem;
import at.spengergasse.spengerbite.model.restaurant.Restaurant;
import at.spengergasse.spengerbite.model.shared.Address;
import at.spengergasse.spengerbite.model.shared.CuisineType;
import at.spengergasse.spengerbite.model.shared.Money;
import at.spengergasse.spengerbite.model.shared.OpeningHours;
import at.spengergasse.spengerbite.repository.MenuItemRepository;
import at.spengergasse.spengerbite.repository.RestaurantRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.time.LocalTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

// Integration test: the whole app on a random port, REST Assured sends real HTTP requests
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantControllerIT {

    @LocalServerPort
    private int port;

    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;

        // Delete in FK order, the demo seed fills the database on boot
        menuItemRepository.deleteAll();
        restaurantRepository.deleteAll();
    }


    // GET /api/restaurants

    @Test
    public void getAllRestaurants_ShouldReturn200WithList_WhenRestaurantsAreSaved() {

        // Given
        restaurantRepository.save(createRestaurant("Pizza Mario", "Wien"));
        restaurantRepository.save(createRestaurant("Burger House", "Graz"));

        // When, Then
        given()
        .when()
            .get("/api/restaurants")
        .then()
            .statusCode(200)
            .body("size()", is(2))
            .body("[0].name", is("Pizza Mario"));
    }


    // GET /api/restaurants/{id}

    @Test
    public void getRestaurantById_ShouldReturn404_WhenIdIsUnknown() {
        // TODO
    }


    // POST /api/restaurants

    @Test
    public void createRestaurant_ShouldReturn201_WhenRequestIsValid() {
        // TODO
    }

    @Test
    public void createRestaurant_ShouldReturn400_WhenNameIsBlank() {
        // TODO
    }


    // DELETE /api/restaurants/{id}

    @Test
    public void deleteRestaurant_ShouldReturn409_WhenRestaurantHasMenuItems() {
        // TODO
    }


    // --- Factories (copied from the repository tests) ---

    private static Restaurant createRestaurant(String name, String city) {
        return new Restaurant(
            name,
            CuisineType.ITALIAN,
            new Address(city, "Spengergasse 20", "1050"),
            new OpeningHours(LocalTime.of(9, 0), LocalTime.of(22, 0)),
            "image.jpg");
    }

    private static MenuItem createMenuItem(Restaurant restaurant, String name, String price) {
        return new MenuItem(restaurant, name, "Tomato, mozzarella, basil",
            new Money(new BigDecimal(price), "EUR"), "image.jpg");
    }
}
