package at.spengergasse.spengerbite.model.payment;

import at.spengergasse.spengerbite.model.BaseEntity;
import at.spengergasse.spengerbite.model.Guard;
import at.spengergasse.spengerbite.model.order.Order;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type")
public abstract class Payment extends BaseEntity {

    // https://vladmihalcea.com/the-best-way-to-map-a-onetoone-association-with-jpa-and-hibernate/
    // @OneToOne(fetch = FetchType.LAZY)
    // @MapsId

    // 1:1 bi, child: FK order_id, not null and unique, exactly one payment per order
    @OneToOne(optional = false)
    @ToString.Exclude
    private Order order;

    // --- Business Ctor ---

    protected Payment(Order order) {
        this.order = Guard.notNull(order, "order");
    }
}
