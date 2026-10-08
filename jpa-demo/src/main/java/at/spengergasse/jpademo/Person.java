package at.spengergasse.jpademo;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person extends BaseEntity {

    private String name;

    @OneToOne(mappedBy = "person")
    @ToString.Exclude
    private Passport passport;

    @OneToMany(mappedBy = "person")
    @ToString.Exclude
    private final List<Trip> trips = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public Passport issuePassport(String number) {
        Passport passport = new Passport(number, this);
        this.passport = passport;
        return passport;
    }

    public Trip addTrip(String destination) {
        Trip trip = new Trip(destination, this);
        trips.add(trip);
        return trip;
    }
}
