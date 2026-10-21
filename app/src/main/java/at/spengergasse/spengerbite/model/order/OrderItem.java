package at.spengergasse.spengerbite.model.order;

import at.spengergasse.spengerbite.model.BaseEntity;
import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.restaurant.MenuItem;
import at.spengergasse.spengerbite.model.shared.Money;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class OrderItem extends BaseEntity {

    // 1:n bi, child of the composition: FK order_id, not null
    @ManyToOne(optional = false)
    @ToString.Exclude
    private Order order;

    // 1:n uni, child: FK menu_item_id, not null
    @ManyToOne(optional = false)
    @ToString.Exclude
    private MenuItem menuItem;

    // Invariant: snapshot of the price at order time
    private Money unitPrice;

    private int quantity;


    // --- Business Ctor ---

    public OrderItem(Order order, MenuItem menuItem, int quantity) {
        this.order = Guard.notNull(order, "order");
        this.menuItem = Guard.notNull(menuItem, "menuItem");
        this.unitPrice = menuItem.getPrice();
        this.quantity = Guard.positive(quantity, "quantity");
    }
}
