package at.spengergasse.spengerbite.model.restaurant;

import at.spengergasse.spengerbite.model.BaseEntity;
import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.shared.Money;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Lombok
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// JPA
@Entity
// compound unique over the FK and a column: one dish name once per restaurant
@Table(uniqueConstraints = @UniqueConstraint(
    name = "uk_menu_item_restaurant_name",
    columnNames = {"restaurant_id", "name"}))
public class MenuItem extends BaseEntity {

    // 1:n uni, child: FK restaurant_id, not null
    @ManyToOne(optional = false)
    @ToString.Exclude
    private Restaurant restaurant;

    private String name;

    private String description;

    private Money price;

    private String imageUrl;


    // --- Business Ctor ---

    public MenuItem(Restaurant restaurant, String name, String description, Money price, String imageUrl) {
        this.restaurant = Guard.notNull(restaurant, "restaurant");
        this.name = Guard.hasText(name, "name");
        this.description = Guard.hasText(description, "description");
        this.price = Guard.notNull(price, "price");
        this.imageUrl = Guard.hasText(imageUrl, "imageUrl");
    }
}
