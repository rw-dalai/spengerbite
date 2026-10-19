package at.spengergasse.spengerbite.model.shared;

import at.spengergasse.spengerbite.model.Guard;
import jakarta.persistence.Embeddable;

@Embeddable
public record Contact(String firstName, String lastName, Phone phone) {

    public Contact {
        firstName = Guard.hasText(firstName, "firstName");
        lastName = Guard.hasText(lastName, "lastName");
        // phone = Guard.notNull(phone, "phone"); can be optional !
    }
}
