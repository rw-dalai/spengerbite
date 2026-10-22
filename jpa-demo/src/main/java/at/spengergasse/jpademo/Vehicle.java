package at.spengergasse.jpademo;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// All subclasses share one table, the column vehicle_type says which class a row is
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type")
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Vehicle extends BaseEntity {

    private String licensePlate;

    // 1:n uni, child: FK owner_id, not null, Person has no list
    @ManyToOne(optional = false)
    @ToString.Exclude
    private Person owner;

    protected Vehicle(String licensePlate, Person owner) {
        this.licensePlate = licensePlate;
        this.owner = owner;
    }
}
