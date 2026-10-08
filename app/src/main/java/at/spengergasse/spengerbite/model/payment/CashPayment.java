package at.spengergasse.spengerbite.model.payment;

import at.spengergasse.spengerbite.model.order.Order;
import at.spengergasse.spengerbite.model.shared.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashPayment extends Payment {

    // Invariant: null = exact amount, no change needed
    private Money changeFor;


    // --- Business Ctor ---

    public CashPayment(Order order, Money changeFor) {
        super(order);
        this.changeFor = changeFor;
    }
}
