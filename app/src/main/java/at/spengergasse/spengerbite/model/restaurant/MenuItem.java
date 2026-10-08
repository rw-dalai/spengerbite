package at.spengergasse.spengerbite.model.restaurant;

import at.spengergasse.spengerbite.model.BaseEntity;
import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.shared.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuItem extends BaseEntity {

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
