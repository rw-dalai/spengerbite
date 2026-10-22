package at.spengergasse.jpademo;

import jakarta.persistence.Entity;
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
public class Passport extends BaseEntity {

    private String number;

    // 1:1 bi, child: FK person_id, not null and unique
    @OneToOne(optional = false)
    @ToString.Exclude
    private Person person;

    public Passport(String number, Person person) {
        this.number = number;
        this.person = person;
    }
}
