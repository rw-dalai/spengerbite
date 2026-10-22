package at.spengergasse.jpademo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Person is the Parent in every relation here
// Parent is the "fachlicher Owner"

// 1 Person has 1 Passport
// 1 Person has n Trips
// n Persons speak m Languages

// Passport is the Child (holds FK)
// Trip is the Child (holds FK)

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person extends BaseEntity {

    private String name;

    // An embeddable value object, it brings the columns street and city
    private Address homeAddress;

    // 1:n value objects, own table person_previous_addresses without id, name from the field
    // Hibernate deletes and rewrites the whole list on every change
    // That is why the core project avoids @ElementCollection
    @ElementCollection
    @ToString.Exclude
    private final List<Address> previousAddresses = new ArrayList<>();

    // 1:1 bi, parent: navigation only, the FK person_id lives in Passport
    @OneToOne(mappedBy = "person")
    @ToString.Exclude
    private Passport passport;

    // 1:n bi, parent of the composition: navigation only, the FK person_id lives in Trip
    // trips die with the person: cascade saves and deletes them with it, orphanRemoval deletes one removed from the list
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private final List<Trip> trips = new ArrayList<>();

    // n:m bi, this side owns the join table person_language, the other side has mappedBy
    @ManyToMany
    @JoinTable(name = "person_language", joinColumns = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id"))
    @ToString.Exclude
    private final Set<Language> languages = new HashSet<>();

    public Person(String name, Address homeAddress) {
        this.name = name;
        this.homeAddress = homeAddress;
    }

    // --- Business Methods ---

    // setup 1:1 relation bidirectional
    public Passport issuePassport(String number) {
        Passport passport = new Passport(number, this);
        this.passport = passport;
        return passport;
    }

    // setup 1:n relation bidirectional
    public Trip addTrip(String destination, LocalDate startDate) {
        Trip trip = new Trip(destination, startDate, this);
        trips.add(trip);
        return trip;
    }

    // orphanRemoval deletes the row of the removed Trip
    public void removeTrip(Trip trip) {
        trips.remove(trip);
    }

    // n:m is set on both sides by the parent
    public void addLanguage(Language language) {
        languages.add(language);
        language.getSpeakers().add(this);
    }

    // The old home becomes a previous address
    public void moveTo(Address newHome) {
        previousAddresses.add(homeAddress);
        this.homeAddress = newHome;
    }
}
