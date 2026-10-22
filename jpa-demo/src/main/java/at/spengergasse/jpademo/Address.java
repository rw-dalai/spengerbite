package at.spengergasse.jpademo;

import jakarta.persistence.Embeddable;

// A value object, it has no id and no table of its own
// Its columns are named after the components and live in the owning table
@Embeddable
public record Address(String street, String city) {
}
