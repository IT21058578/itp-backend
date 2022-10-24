package com.web.backend.controllers.payment;

import com.web.backend.model.payment.Payment;
import com.web.backend.services.payment.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    //Create
    @PostMapping
    public void createNewPayment(@RequestBody Payment payment){paymentService.createNewPayment(payment);}

    //Read
    @GetMapping(params = {"invoiceId"})
    public List<Payment> getPaymentByInvoice(@RequestParam ("invoiceId") String invoiceId){
        return paymentService.getPaymentsByInvoiceId(invoiceId);
    }

    @GetMapping(params = {"email"})
    public List<Payment> getPaymentsByEmail(@RequestParam ("email") String email){
        return paymentService.getPaymentsByEmail(email);
    }

    @GetMapping(params = {"cardNumber"})
    public List<Payment> getPaymentByCard(@RequestParam ("cardNumber") String cardNumber){
        return paymentService.getPaymentsByCard(cardNumber);
    }

    @GetMapping(params = {"id"})
    public Payment getPaymentById(@RequestParam ("id") String id){
        return paymentService.getPaymentById(id);
    }



    //Update
    @PutMapping
    public ResponseEntity<Payment> updatePaymentById(@RequestParam ("id")String id, @RequestBody Payment payment){
        return paymentService.updatePayment(id, payment);
    }

    //Delete
    @DeleteMapping
    public void deletePaymentById(@RequestParam ("id")String id){
        paymentService.deletePaymentById(id);
    }
}
