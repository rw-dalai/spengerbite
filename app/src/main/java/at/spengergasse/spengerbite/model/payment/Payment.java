package at.spengergasse.spengerbite.model.payment;

import at.spengergasse.spengerbite.model.BaseEntity;
import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.order.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Payment extends BaseEntity {

    // Invariant: exactly one payment per order
    @ToString.Exclude
    private Order order;

    // --- Business Ctor ---

    protected Payment(Order order) {
        this.order = Guard.notNull(order, "order");
    }
}
