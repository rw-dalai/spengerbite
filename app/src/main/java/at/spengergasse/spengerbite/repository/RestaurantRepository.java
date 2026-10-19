package at.spengergasse.spengerbite.repository;


import at.spengergasse.spengerbite.model.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // JPA Query Methods
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    // Basic Syntax: findBy/existsBy + PropertyName + (Optional: Operator)
    // Operator: Containing, IgnoreCase, Before, After, Between, LessThan, GreaterThan, etc.
    // For example: findByName, findByAddressCity, findByOpeningHoursOpenTimeBefore

    // List<Restaurant> findByName(String name);
    // List<Restaurant> findByAddress_City(String city); // _ makes the property path explicit

    public List<Restaurant> findByAddressCity(String city);
    public List<Restaurant> findByAddressCityContainingIgnoreCase(String city);
}
