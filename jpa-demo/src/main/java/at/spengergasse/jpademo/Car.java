package at.spengergasse.jpademo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@DiscriminatorValue("CAR")
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Car extends Vehicle {

    // Single table, the columns of the children must stay nullable, never @Column(nullable = false) here
    // Hibernate only adds a check for rows of this type, a Bike row has no seats
    private int seats;

    public Car(String licensePlate, Person owner, int seats) {
        super(licensePlate, owner);
        this.seats = seats;
    }
}
