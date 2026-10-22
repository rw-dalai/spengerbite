package at.spengergasse.spengerbite.model.payment;

import at.spengergasse.spengerbite.model.order.Order;
import at.spengergasse.spengerbite.model.shared.Money;
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
@DiscriminatorValue("CASH")
public class CashPayment extends Payment {

    // Invariant: null = exact amount, no change needed
    // Single table: child columns must stay nullable, never @Column(nullable = false) here
    private Money changeFor;


    // --- Business Ctor ---

    public CashPayment(Order order, Money changeFor) {
        super(order);
        this.changeFor = changeFor;
    }
}
