package at.spengergasse.spengerbite.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

// Lombok
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// JPA
@MappedSuperclass
public abstract class BaseEntity {

    // --- Id ---

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    // @GeneratedValue(strategy = GenerationType.SEQUENCE) // sequence
    private Long id;

    // --- Audit ---

    @CreationTimestamp // Hibernate sets it on insert
    private Instant createdAt;

    @UpdateTimestamp // Hibernate sets it on every update
    private Instant updatedAt;

    // TODO equals and hashCode
}
