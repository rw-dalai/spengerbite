package at.spengergasse.spengerbite.api.restaurant;

// Bonus
// Request DTO for PATCH: only the opening hours
// TODO fields: opensAt, closesAt
// TODO Bean Validation: @NotNull for both
// TODO cross attribute check: opensAt must be before closesAt (@AssertTrue on a method)
public record ChangeOpeningHoursRequest() { }
