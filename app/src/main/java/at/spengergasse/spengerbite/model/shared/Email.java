package at.spengergasse.spengerbite.model.shared;

import at.spengergasse.spengerbite.model.Guard;

public record Email(String value) {

    public Email {
        value = Guard.email(value, "email");
    }
}
