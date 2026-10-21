package at.spengergasse.spengerbite.model.shared;

import at.spengergasse.spengerbite.model.Guard;

// immutable, getters, toString, equals, hashCode
public record Phone(String value) {

    // compact constructor
    public Phone {
        value = Guard.phone(value, "phone");
    }
}
