package at.spengergasse.spengerbite.api.restaurant;

// Request DTO: what the client sends
// TODO fields: name, cuisine, city, street, zip, opensAt, closesAt, imageUrl
// TODO Bean Validation: @NotBlank for texts, @NotNull for cuisine, opensAt, closesAt
// TODO cross attribute check: opensAt must be before closesAt (@AssertTrue on a method)
public record CreateRestaurantRequest() { }
