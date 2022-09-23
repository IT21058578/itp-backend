package com.web.backend.services;

import com.web.backend.model.payment.Invoice;
import com.web.backend.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    //Read
    public Iterable<Invoice> getAllInvoice(){
        return invoiceRepository.findAll();
    }

    public Invoice findInvoiceById(String id){
        return invoiceRepository.findInvoiceById(id);
    }

    //Save or Update
    public Invoice saveOrUpdateInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }
    //Delete
    public void deleteInvoiceById(String id){
        invoiceRepository.deleteById(id);
    }
}
