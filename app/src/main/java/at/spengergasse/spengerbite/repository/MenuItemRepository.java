package at.spengergasse.spengerbite.repository;

import at.spengergasse.spengerbite.model.restaurant.MenuItem;
import at.spengergasse.spengerbite.model.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    // the menu of one restaurant, Restaurant has no list of its own
    List<MenuItem> findByRestaurant(Restaurant restaurant);
}
