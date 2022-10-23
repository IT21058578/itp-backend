package com.web.backend.repositories.payment;

import com.web.backend.model.payment.Instrument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InstrumentRepository extends MongoRepository<Instrument, String> {
    List<Instrument> findInstrumentById(String id);
    List<Instrument> findInstrumentsByCardNumber(String cardNumber);
    List<Instrument> findInstrumentsByEmail(String email);
}
