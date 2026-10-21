package at.spengergasse.spengerbite.model.customer;

import at.spengergasse.spengerbite.model.BaseEntity;
import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.shared.Contact;
import at.spengergasse.spengerbite.model.shared.Email;
import at.spengergasse.spengerbite.model.shared.PasswordHash;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Lombok
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// JPA
// unique index with a name, the error handler maps uk_customer_email to a message later
@Entity
@Table(uniqueConstraints = @UniqueConstraint(
    name = "uk_customer_email", columnNames = "email"))
public class Customer extends BaseEntity {

    private String name;

    // Invariant: the login, unique
    // @Column(unique = true)
    private Email email;

    @ToString.Exclude
    private PasswordHash passwordHash;

    // embedded value object, phone inside goes through the PhoneConverter
    private Contact contact;


    // --- Business Ctor ---

    public Customer(Email email, PasswordHash passwordHash, Contact contact) {
        this.email = Guard.notNull(email, "email");
        this.passwordHash = Guard.notNull(passwordHash, "passwordHash");
        this.contact = Guard.notNull(contact, "contact");
    }
}
