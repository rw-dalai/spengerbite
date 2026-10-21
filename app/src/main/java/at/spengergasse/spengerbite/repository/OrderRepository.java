package at.spengergasse.spengerbite.repository;

import at.spengergasse.spengerbite.model.customer.Customer;
import at.spengergasse.spengerbite.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // my orders, newest first, served by ix_orders_customer_created
    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);
}
