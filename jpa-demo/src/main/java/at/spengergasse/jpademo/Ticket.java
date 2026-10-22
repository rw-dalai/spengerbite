package at.spengergasse.jpademo;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends BaseEntity {

    private String seat;

    // 1:1 uni, child: FK trip_id, not null and unique, Trip has no field
    @OneToOne(optional = false)
    @ToString.Exclude
    private Trip trip;

    public Ticket(String seat, Trip trip) {
        this.seat = seat;
        this.trip = trip;
    }
}
