package at.spengergasse.spengerbite.model.shared;

import at.spengergasse.spengerbite.model.Guard;
import jakarta.persistence.Embeddable;

import java.time.LocalTime;

@Embeddable
public record OpeningHours(LocalTime opensAt, LocalTime closesAt) {

    public OpeningHours {

        Guard.notNull(opensAt, "opensAt");
        Guard.notNull(closesAt, "closesAt");

        // Prof. Lorenz: Does not support overnight opening hours, e.g. 22:00 - 06:00 :-)
        Guard.isTrue(opensAt.isBefore(closesAt),
            "opensAt must be before closesAt, but was " + opensAt + " and " + closesAt);
    }
}
