package at.spengergasse.spengerbite.repository;

import at.spengergasse.spengerbite.model.customer.Customer;
import at.spengergasse.spengerbite.model.shared.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // the finder takes the rich type, the converter translates the parameter too
    Optional<Customer> findByEmail(Email email);

    boolean existsByEmail(Email email);
}
