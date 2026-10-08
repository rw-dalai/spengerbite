package at.spengergasse.spengerbite.model.customer;

import at.spengergasse.spengerbite.model.BaseEntity;
import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.shared.Contact;
import at.spengergasse.spengerbite.model.shared.Email;
import at.spengergasse.spengerbite.model.shared.PasswordHash;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends BaseEntity {

    // Invariant: the login, unique
    private Email email;

    @ToString.Exclude
    private PasswordHash passwordHash;

    private Contact contact;


    // --- Business Ctor ---

    public Customer(Email email, PasswordHash passwordHash, Contact contact) {
        this.email = Guard.notNull(email, "email");
        this.passwordHash = Guard.notNull(passwordHash, "passwordHash");
        this.contact = Guard.notNull(contact, "contact");
    }
}
