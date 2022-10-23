package com.web.backend.repositories.payment;

import com.web.backend.model.payment.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findPaymentsByCardNumber(String cardNumber);
    List<Payment> findPaymentsByEmail(String email);
}
