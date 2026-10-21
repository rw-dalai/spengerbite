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
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Lombok
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// JPA
// "order" is a reserved word in SQL, so the table is called orders
@Entity
// compound index with the FK first: my orders of one customer, newest first
@Table(name = "orders", indexes = @Index(
    name = "ix_orders_customer_created", columnList = "customer_id, created_at"))
public class Order extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 1:n uni, child: FK restaurant_id, not null, only one restaurant per order
    @ManyToOne(optional = false)
    @ToString.Exclude
    private Restaurant restaurant;

    // 1:n uni, child: FK customer_id, nullable, null = guest order
    @ManyToOne
    @ToString.Exclude
    private Customer customer;

    // Invariant: snapshot
    private Email email;

    // Invariant: snapshot
    private Contact contact;

    // Invariant: snapshot
    private Address deliveryAddress;

    // 1:1 bi, parent: navigation only, the FK order_id lives in Payment, null = not paid yet
    @OneToOne(mappedBy = "order")
    @ToString.Exclude
    private Payment payment;

    // 1:n bi, parent of the composition: navigation only, the FK order_id lives in OrderItem
    // items die with the order: cascade saves and deletes them with it, orphanRemoval deletes one removed from the list
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
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
