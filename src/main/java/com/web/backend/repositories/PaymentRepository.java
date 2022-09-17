package com.web.backend.repositories;

import com.web.backend.model.payment.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
