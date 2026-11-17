package at.spengergasse.spengerbite.api.customer;

// Request DTO: what the client sends
// TODO fields: email, password, firstName, lastName, phone
// TODO Bean Validation: @NotBlank for all, @Email for email
public record RegisterCustomerRequest() { }
