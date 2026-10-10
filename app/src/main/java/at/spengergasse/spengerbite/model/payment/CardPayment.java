package at.spengergasse.spengerbite.model.payment;

import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.order.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardPayment extends Payment {

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
