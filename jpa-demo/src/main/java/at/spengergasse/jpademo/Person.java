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

// Person is the Parent in both relations
// Parent is the "fachlicher Owner"

// 1 Person has 1 Passport
// 1 Person has n Trips

// Passport is the Child (holds FK)
// Trip is the Child (holds FK)

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person extends BaseEntity {

    private String name;

    // This is only a navigation property
    // mappedBy = "person" means the other side has the FK
    @OneToOne(mappedBy = "person")
    @ToString.Exclude
    private Passport passport;

    // This is only a navigation property
    // mappedBy = "person" means the other side has the FK
    // orphanRemoval = true means if a Trip is removed from the list, it will be deleted from the DB
    @OneToMany(mappedBy = "person", orphanRemoval = true)
    @ToString.Exclude
    private final List<Trip> trips = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    // --- Business Methods ---

    // setup 1:1 relation bidirectional
    public Passport issuePassport(String number) {
        Passport passport = new Passport(number, this);
        this.passport = passport;
        return passport;
    }

    // setup 1:n relation bidirectional
    public Trip addTrip(String destination) {
        Trip trip = new Trip(destination, this);
        trips.add(trip);
        return trip;
    }
}
