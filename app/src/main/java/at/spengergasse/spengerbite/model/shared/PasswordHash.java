package at.spengergasse.spengerbite.model.shared;

import at.spengergasse.spengerbite.model.Guard;

public record PasswordHash(String value) {

    public PasswordHash {
        value = Guard.hasText(value, "passwordHash");
    }
}
