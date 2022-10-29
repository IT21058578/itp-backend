package com.web.backend.services.payment;
import com.web.backend.model.payment.Payment;
import com.web.backend.repositories.payment.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    //Create
    public void createNewPayment(Payment payment){paymentRepository.save(payment);}

    //Read
    public Payment getPaymentById(String id){return paymentRepository.findPaymentById(id);}

    public List<Payment> getPaymentsByEmail(String email){return paymentRepository.findPaymentsByEmail(email);}

    public List<Payment> getPaymentsByCard(String cardNumber){return paymentRepository.findPaymentsByCardNumber(cardNumber);}

    public List<Payment> getPaymentsByInvoiceId(String invoiceId){return paymentRepository.findPaymentsByInvoiceId(invoiceId);}

    //Update
    public ResponseEntity<Payment> updatePayment(String id, Payment payment){
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if(paymentOptional.isPresent()){
            Payment _payment = paymentOptional.get();
            _payment.setPaymentStatus(payment.isPaymentStatus());

            return new ResponseEntity<>(paymentRepository.save(_payment), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete
    public void deletePaymentById(String id){paymentRepository.deleteById(id);}
}
