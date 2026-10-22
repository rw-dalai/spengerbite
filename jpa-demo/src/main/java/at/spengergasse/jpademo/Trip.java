package at.spengergasse.jpademo;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trip extends BaseEntity {

    private String destination;

    private LocalDate startDate;

    // 1:n bi, child of the composition: FK person_id, not null
    @ManyToOne(optional = false)
    @ToString.Exclude
    private Person person;

    public Trip(String destination, LocalDate startDate, Person person) {
        this.destination = destination;
        this.startDate = startDate;
        this.person = person;
    }
}
