package at.spengergasse.spengerbite.model.order;

import at.spengergasse.spengerbite.model.BaseEntity;
import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.customer.Customer;
import at.spengergasse.spengerbite.model.payment.Payment;
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

    // Invariant: at least one item, items die with the order
    @Getter(AccessLevel.NONE)
    private final List<OrderItem> items = new ArrayList<>();


    // --- Business Ctor ---

    public Order(Restaurant restaurant, Customer customer, Email email, Contact contact, Address deliveryAddress, List<OrderItem> items) {
        this.status = OrderStatus.SUBMITTED;
        this.restaurant = Guard.notNull(restaurant, "restaurant");
        this.customer = customer;
        this.email = Guard.notNull(email, "email");
        this.contact = Guard.notNull(contact, "contact");
        this.deliveryAddress = Guard.notNull(deliveryAddress, "deliveryAddress");
        this.items.addAll(Guard.notEmpty(items, "items"));
        // TODO business rule: all items belong to this restaurant
    }


    // --- Queries ---

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
