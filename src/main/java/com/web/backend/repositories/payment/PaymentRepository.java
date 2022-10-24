package com.web.backend.repositories.payment;

import com.web.backend.model.payment.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    Payment findPaymentById(String id);
    List<Payment> findPaymentsByInvoiceId(String invoiceId);
    List<Payment> findPaymentsByCardNumber(String cardNumber);
    List<Payment> findPaymentsByEmail(String email);
}
