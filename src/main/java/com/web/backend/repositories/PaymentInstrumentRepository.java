package com.web.backend.repositories;

import com.web.backend.model.payment.PaymentInstrument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentInstrumentRepository extends MongoRepository<PaymentInstrument, String> {
}
