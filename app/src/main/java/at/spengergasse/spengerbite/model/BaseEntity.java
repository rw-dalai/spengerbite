package at.spengergasse.spengerbite.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

    // --- Id ---

    private Long id;

    // --- Audit ---

    private Instant createdAt;

    private Instant updatedAt;

    // TODO equals and hashCode
}
