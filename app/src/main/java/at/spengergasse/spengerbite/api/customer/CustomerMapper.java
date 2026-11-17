package at.spengergasse.spengerbite.api.customer;

import at.spengergasse.spengerbite.model.customer.Customer;
import at.spengergasse.spengerbite.model.shared.Contact;
import at.spengergasse.spengerbite.model.shared.Email;
import at.spengergasse.spengerbite.model.shared.PasswordHash;
import at.spengergasse.spengerbite.model.shared.Phone;

// Mapper: translates between DTOs and the domain, no logic, no checks (the domain guards itself)
public final class CustomerMapper {

    private CustomerMapper() { }

    // Entity -> Response DTO
    public static CustomerResponse toResponse(Customer customer) {
        // TODO the phone is optional in the domain, it can be null
        throw new UnsupportedOperationException("TODO");
    }

    // Request DTO -> Entity
    public static Customer toEntity(RegisterCustomerRequest request) {
        // TODO
        // The password goes into PasswordHash as it is, hashing comes later with the PasswordService
        throw new UnsupportedOperationException("TODO");
    }
}
