package at.spengergasse.jpademo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@DiscriminatorValue("BIKE")
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bike extends Vehicle {

    // Single table, the columns of the children must stay nullable, never @Column(nullable = false) here
    // Hibernate only adds a check for rows of this type, a Car row has no electric flag
    private boolean electric;

    public Bike(String licensePlate, Person owner, boolean electric) {
        super(licensePlate, owner);
        this.electric = electric;
    }
}
