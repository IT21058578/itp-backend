package com.web.backend.repositories.payment;

import com.stripe.model.Token;
import com.web.backend.model.payment.Instrument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepository extends MongoRepository<Instrument, String> {
    Instrument findInstrumentById(String id);
    List<Instrument> findInstrumentsByCardNumber(String cardNumber);
    List<Instrument> findInstrumentsByEmail(String email);

}
