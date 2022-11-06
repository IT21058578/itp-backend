package com.web.backend.services.payment;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Token;
import com.web.backend.model.payment.Instrument;
import com.web.backend.repositories.payment.InstrumentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InstrumentService {
    private final InstrumentRepository instrumentRepository;

    public InstrumentService(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    //Create
    public void addNewInstrument(Instrument instrument){
        instrumentRepository.save(instrument);
    }

    //Read
    public Iterable<Instrument> getAllInstrumentsByEmail(String email){
        return instrumentRepository.findInstrumentsByEmail(email);
    }

    public List<Instrument> getInstrumentByCard(String cardNumber){
        return instrumentRepository.findInstrumentsByCardNumber(cardNumber);
    }

    public Instrument getInstrumentById(String id){
        return instrumentRepository.findInstrumentById(id);
    }


    //Update
    public ResponseEntity<Instrument> updateInstrument(String id, Instrument instrument){
        Optional<Instrument> instrumentOptional = instrumentRepository.findById(id);

        if(instrumentOptional.isPresent()){
            Instrument _instrument = instrumentOptional.get();
            _instrument.setExpiryDate(instrument.getExpiryDate());
            _instrument.setCvv(instrument.getCvv());
            _instrument.setCardType(instrument.getCardType());

            return new ResponseEntity<>(instrumentRepository.save(_instrument), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete
    public void deleteInstrumentById(String id){
        instrumentRepository.deleteById(id);
    }


    //Get Stripe Token
    public Token getTokenFromStripe(Instrument instrument) throws RuntimeException {
        Stripe.apiKey = "sk_test_51Ln0qXJfShQL4M87LrrKYsNhdCGqUFY4uCerloEyDKsXIHJtPeLCtVSvNcUPctZTRZUDO7P2AckhJkUGp1HR8zrs00oh4t7FHx";
        String expMonth = new String(String.valueOf(instrument.getExpiryDate().charAt(0) + instrument.getExpiryDate().charAt(1)));
        String expYear = new String("20" + String.valueOf(instrument.getExpiryDate().charAt(3) + instrument.getExpiryDate().charAt(4)));
        Map<String, Object> card = new HashMap<>();
        card.put("number", instrument.getCardNumber());
        card.put("exp_month", expMonth);
        card.put("exp_year", instrument.getExpiryDate());
        card.put("cvc", instrument.getCvv());
        Map<String, Object> params = new HashMap<>();
        params.put("card", card);

        Token token;
        try {
            token = Token.create(params);
        } catch (AuthenticationException | APIConnectionException | CardException | APIException |
                 InvalidRequestException e) {
            throw new RuntimeException(e);
        }


        return token;
    }

}
