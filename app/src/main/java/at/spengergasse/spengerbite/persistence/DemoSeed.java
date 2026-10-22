package at.spengergasse.spengerbite.persistence;

import at.spengergasse.spengerbite.model.customer.Customer;
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
import at.spengergasse.spengerbite.repository.CustomerRepository;
import at.spengergasse.spengerbite.repository.MenuItemRepository;
import at.spengergasse.spengerbite.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;

// Runs once after boot and fills the in memory database, so the H2 console has rows to look at
@Component
public class DemoSeed implements CommandLineRunner {

    private final RestaurantRepository restaurants;
    private final MenuItemRepository menuItems;
    private final CustomerRepository customers;

    public DemoSeed(RestaurantRepository restaurants, MenuItemRepository menuItems, CustomerRepository customers) {
        this.restaurants = restaurants;
        this.menuItems = menuItems;
        this.customers = customers;
    }

    @Override
    public void run(String... args) {
        Restaurant mario = restaurants.save(new Restaurant("Pizzeria Mario", CuisineType.ITALIAN,
            new Address("Wien", "Spengergasse 20", "1050"),
            new OpeningHours(LocalTime.of(11, 0), LocalTime.of(22, 0)),
            "/images/mario.jpg"));
        menuItems.save(new MenuItem(mario, "Margherita", "Tomato, mozzarella, basil", eur("9.90"), "/images/margherita.jpg"));
        menuItems.save(new MenuItem(mario, "Diavola", "Spicy salami, chili", eur("11.50"), "/images/diavola.jpg"));

        Restaurant ko = restaurants.save(new Restaurant("Sushi Ko", CuisineType.JAPANESE,
            new Address("Wien", "Margaretenstrasse 5", "1040"),
            new OpeningHours(LocalTime.of(12, 0), LocalTime.of(23, 0)),
            "/images/ko.jpg"));
        menuItems.save(new MenuItem(ko, "Sake Nigiri", "Salmon on rice", eur("6.80"), "/images/sake.jpg"));
        menuItems.save(new MenuItem(ko, "Maki Mix", "Twelve pieces", eur("12.40"), "/images/maki.jpg"));

        customers.save(new Customer(new Email("max@spengergasse.at"), new PasswordHash("$2a$10$seed"),
            new Contact("Max", "Mustermann", new Phone("0660 123 45 67"))));
    }

    private static Money eur(String amount) {
        return new Money(new BigDecimal(amount), "EUR");
    }
}
