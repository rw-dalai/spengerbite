package at.spengergasse.jpademo;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo extends BaseEntity {

    private String url;

    // 1:n uni, child: FK trip_id, not null, Trip has no list
    @ManyToOne(optional = false)
    @ToString.Exclude
    private Trip trip;

    public Photo(String url, Trip trip) {
        this.url = url;
        this.trip = trip;
    }
}
