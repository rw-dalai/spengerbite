package at.spengergasse.spengerbite.model.payment;

import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Lombok
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// JPA
@Entity
@DiscriminatorValue("CARD")
public class CardPayment extends Payment {

    // Single table: child columns must stay nullable, never @Column(nullable = false) here
    // @Column(name = "my_brand", nullable = false, unique = true)
    private String brand;

    // Invariant: the last four digits only.
    private String cardLast4;


    // --- Business Ctor ---

    public CardPayment(Order order, String brand, String cardLast4) {
        super(order);
        this.brand = Guard.hasText(brand, "brand");
        this.cardLast4 = Guard.hasLength(cardLast4, 4, "cardLast4");
    }
}
