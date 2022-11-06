package com.web.backend.services.payment;

import com.web.backend.model.payment.Instrument;
import com.web.backend.repositories.payment.InstrumentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
