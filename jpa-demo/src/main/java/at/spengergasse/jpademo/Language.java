package at.spengergasse.jpademo;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Language extends BaseEntity {

    private String code;

    // n:m bi, navigation only, the join table belongs to Person
    @ManyToMany(mappedBy = "languages")
    @ToString.Exclude
    private final Set<Person> speakers = new HashSet<>();

    public Language(String code) {
        this.code = code;
    }
}
