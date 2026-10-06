package at.spengergasse.spengerbite.model.shared;

import at.spengergasse.spengerbite.model.Guard;

public record Address(String city, String street, String zip) {

    public Address {
        city = Guard.hasText(city, "city");
        street = Guard.hasText(street, "street");
        zip = Guard.hasText(zip, "zip");
    }
}
