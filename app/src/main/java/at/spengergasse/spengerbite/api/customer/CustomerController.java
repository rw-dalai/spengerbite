package at.spengergasse.spengerbite.api.customer;

import at.spengergasse.spengerbite.model.customer.Customer;
import at.spengergasse.spengerbite.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

// REST API: Customers
//
// Verb    Path                  Business logic        Success                  Error
// GET     /api/customers/{id}   get one customer      200 OK                   404
// POST    /api/customers        register a customer   201 Created + Location   400, 409
// DELETE  /api/customers/{id}   delete a customer     204 No Content           404
//
// 400 invalid input | 404 id not found | 409 valid, but the current state forbids it

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customers;

    public CustomerController(CustomerRepository customers) {
        this.customers = customers;
    }


    // GET /api/customers/{id}
    // -> 200 OK         CustomerResponse
    // -> 404 Not Found  ProblemDetail
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        // TODO Fetch, Guard: 404, map + respond
        throw new UnsupportedOperationException("TODO");
    }


    // POST /api/customers
    // -> 201 Created      CustomerResponse, Location header
    // -> 400 Bad Request  ProblemDetail
    // -> 409 Conflict     ProblemDetail
    @PostMapping
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody RegisterCustomerRequest request) {
        // @Valid: Spring answers invalid requests with 400 before this method runs
        // TODO try: map to entity, save, respond 201 + Location
        // TODO catch IllegalArgumentException (thrown by the domain): respond 400
        // TODO catch DataIntegrityViolationException (email already taken): respond 409
        throw new UnsupportedOperationException("TODO");
    }


    // DELETE /api/customers/{id}
    // -> 204 No Content  no body
    // -> 404 Not Found   ProblemDetail
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        // TODO Guard: 404
        // TODO delete, respond 204
        throw new UnsupportedOperationException("TODO");
    }


    // Helper: error response as ProblemDetail
    private static ResponseEntity<?> problem(HttpStatus status, String detail) {
        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(status, detail)).build();
    }
}
