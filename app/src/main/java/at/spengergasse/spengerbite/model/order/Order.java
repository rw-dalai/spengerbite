package at.spengergasse.spengerbite.model.order;

import at.spengergasse.spengerbite.model.BaseEntity;
import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.customer.Customer;
import at.spengergasse.spengerbite.model.payment.Payment;
import at.spengergasse.spengerbite.model.restaurant.MenuItem;
import at.spengergasse.spengerbite.model.restaurant.Restaurant;
import at.spengergasse.spengerbite.model.shared.Address;
import at.spengergasse.spengerbite.model.shared.Contact;
import at.spengergasse.spengerbite.model.shared.Email;
import at.spengergasse.spengerbite.model.shared.OrderStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    private OrderStatus status;

    // Invariant: only one restaurant per order
    @ToString.Exclude
    private Restaurant restaurant;

    // Invariant: null = guest order
    @ToString.Exclude
    private Customer customer;

    // Invariant: snapshot
    private Email email;

    // Invariant: snapshot
    private Contact contact;

    // Invariant: snapshot
    private Address deliveryAddress;

    // Invariant: null = not paid yet, exactly one payment once paid
    @ToString.Exclude
    private Payment payment;

    @Getter(AccessLevel.NONE)
    private final List<OrderItem> items = new ArrayList<>();


    // --- Business Ctor ---

    public Order(Restaurant restaurant, Customer customer, Email email, Contact contact, Address deliveryAddress) {
        this.status = OrderStatus.SUBMITTED;
        this.restaurant = Guard.notNull(restaurant, "restaurant");
        this.customer = customer;
        this.email = Guard.notNull(email, "email");
        this.contact = Guard.notNull(contact, "contact");
        this.deliveryAddress = Guard.notNull(deliveryAddress, "deliveryAddress");
        // TODO business rules in place(): at least one item, all items from this restaurant
    }


    // --- Business Methods ---

    // Order is the parent, OrderItem is the child. Parent sets both sides.
    public OrderItem addItem(MenuItem menuItem, int quantity) {
        // TODO business rule: menuItem belongs to this restaurant
        OrderItem item = new OrderItem(this, menuItem, quantity);
        items.add(item);
        return item;
    }


    // --- Queries ---

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
