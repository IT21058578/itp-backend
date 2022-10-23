package com.web.backend.services;

import com.web.backend.model.payment.Invoice;
import com.web.backend.repositories.payment.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    //Read all Invoices
    public Iterable<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    //Return Invoice from ID
    public Invoice findInvoiceById(String id) {
        return invoiceRepository.findInvoiceById(id);
    }

    //Return Invoices based on the email.
    public List<Invoice> findInvoiceByEmail(String email) {
        return invoiceRepository.findInvoiceByEmail(email);
    }

    //Create New Invoice
    public void addNewInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    //Delete
    public void deleteInvoiceById(String id) {
        invoiceRepository.deleteById(id);
    }

    //Update
    public ResponseEntity<Invoice> updateInvoice(String id, Invoice invoice) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);

        if (invoiceOptional.isPresent()) {
            Invoice _invoice = invoiceOptional.get();
            _invoice.setPaymentStatus(invoice.isPaymentStatus());

            return new ResponseEntity<>(invoiceRepository.save(_invoice), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
