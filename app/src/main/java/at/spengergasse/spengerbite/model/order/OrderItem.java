package at.spengergasse.spengerbite.model.order;

import at.spengergasse.spengerbite.model.BaseEntity;
import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.restaurant.MenuItem;
import at.spengergasse.spengerbite.model.shared.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {

    @ToString.Exclude
    private MenuItem menuItem;

    // Invariant: snapshot of the price at order time
    private Money unitPrice;

    private int quantity;


    // --- Business Ctor ---

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = Guard.notNull(menuItem, "menuItem");
        this.unitPrice = menuItem.getPrice();
        this.quantity = Guard.positive(quantity, "quantity");
    }
}
