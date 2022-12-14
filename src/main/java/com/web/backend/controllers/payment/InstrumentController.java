package com.web.backend.controllers.payment;

import com.stripe.model.Token;
import com.web.backend.model.payment.Instrument;
import com.web.backend.services.payment.InstrumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/instrument")
@AllArgsConstructor
public class InstrumentController {
    private final InstrumentService instrumentService;

    @GetMapping(params ={"email"})
    public Iterable<Instrument> fetchAllInvoiceByEmail(@RequestParam("email") String email){
        return instrumentService.getAllInstrumentsByEmail(email);
    }

    @GetMapping(params ={"id"})
    public Instrument getInstrumentByID(@RequestParam("id") String id){
        return instrumentService.getInstrumentById(id);
    }

    @GetMapping(params={"instId"})
    public Token getTokenFromStripe(@RequestParam("instId")String id, @RequestBody Instrument instrument){
        return instrumentService.getTokenFromStripe(instrument);
    }

    @PostMapping
    public void createNewInstrument(@RequestBody Instrument instrument){
        instrumentService.addNewInstrument(instrument);
    }

    @PutMapping
    public ResponseEntity<Instrument> updateInstrumentById(@RequestParam ("id") String id, @RequestBody Instrument instrument){
        return instrumentService.updateInstrument(id, instrument);
    }

    @DeleteMapping
    public void deleteInstrumentById(@RequestParam ("id") String id){
        instrumentService.deleteInstrumentById(id);
    }

}
