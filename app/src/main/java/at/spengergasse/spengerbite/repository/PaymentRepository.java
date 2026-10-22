package at.spengergasse.spengerbite.repository;

import at.spengergasse.spengerbite.model.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
