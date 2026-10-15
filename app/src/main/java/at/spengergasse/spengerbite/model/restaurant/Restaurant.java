package at.spengergasse.spengerbite.model.restaurant;

import at.spengergasse.spengerbite.model.BaseEntity;
import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.shared.Address;
import at.spengergasse.spengerbite.model.shared.CuisineType;
import at.spengergasse.spengerbite.model.shared.OpeningHours;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Restaurant extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private CuisineType cuisine;

    private Address address;

    private OpeningHours openingHours;

    private String imageUrl;


    // --- Business Ctor ---

    public Restaurant(String name, CuisineType cuisine, Address address, OpeningHours openingHours, String imageUrl) {
        this.name = Guard.hasText(name, "name");
        this.cuisine = Guard.notNull(cuisine, "cuisine");
        this.address = Guard.notNull(address, "address");
        this.openingHours = Guard.notNull(openingHours, "openingHours");
        this.imageUrl = Guard.hasText(imageUrl, "imageUrl");
    }
}
