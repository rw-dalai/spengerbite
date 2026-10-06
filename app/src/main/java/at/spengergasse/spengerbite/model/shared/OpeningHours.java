package at.spengergasse.spengerbite.model.shared;

import at.spengergasse.spengerbite.model.Guard;

import java.time.LocalTime;

public record OpeningHours(LocalTime opensAt, LocalTime closesAt) {

    public OpeningHours {

        Guard.notNull(opensAt, "opensAt");
        Guard.notNull(closesAt, "closesAt");

        Guard.isTrue(opensAt.isBefore(closesAt),
            "opensAt must be before closesAt, but was " + opensAt + " and " + closesAt);
    }
}
