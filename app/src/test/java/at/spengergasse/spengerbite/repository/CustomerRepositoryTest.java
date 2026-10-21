package at.spengergasse.spengerbite.repository;

import at.spengergasse.spengerbite.model.customer.Customer;
import at.spengergasse.spengerbite.model.shared.Contact;
import at.spengergasse.spengerbite.model.shared.Email;
import at.spengergasse.spengerbite.model.shared.PasswordHash;
import at.spengergasse.spengerbite.model.shared.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    // Save and Retrieve through the converters: Email, PasswordHash, Phone inside Contact
    @Test
    public void findByEmail_ShouldReturnCustomer_WhenCustomerIsSaved() {

        // Given
        customerRepository.save(createCustomer("max@spengergasse.at"));
        entityManager.clear();

        // When
        Customer loaded = customerRepository.findByEmail(new Email("max@spengergasse.at")).orElseThrow();

        // Then
        assertEquals("Max", loaded.getContact().firstName());
        assertEquals("+436601234567", loaded.getContact().phone().value());
    }

    // Unique index: the database is the law, the guard lowercases so the duplicate is caught
    @Test
    public void save_ShouldFail_WhenEmailAlreadyExists() {

        // Given
        customerRepository.save(createCustomer("max@spengergasse.at"));

        // When, Then
        assertThrows(DataIntegrityViolationException.class,
            () -> customerRepository.saveAndFlush(createCustomer("Max@Spengergasse.AT")));
    }


    // --- Private Setup Helpers ---

    private static Customer createCustomer(String email) {
        return new Customer(
            new Email(email),
            new PasswordHash("$2a$10$hash"),
            new Contact("Max", "Mustermann", new Phone("0660 123 45 67")));
    }
}
