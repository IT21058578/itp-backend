package com.web.backend.controllers.payment;

import com.web.backend.model.payment.Invoice;
import com.web.backend.model.payment.Payment;
import com.web.backend.services.payment.InvoiceService;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/invoice")
@AllArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping(params = {"email"})
    public List<Invoice> fetchAllInvoices(@RequestParam ("email") String email){
        return invoiceService.findInvoiceByEmail(email);
    }

    @GetMapping(params = {"invoiceId"})
    public Invoice getPaymentByInvoice(@RequestParam ("invoiceId") String invoiceId){
        return invoiceService.findInvoiceById(invoiceId);
    }

    @PostMapping
    public void addNewInvoice(@RequestBody Invoice invoice){
        invoiceService.addNewInvoice(invoice);
    }

    @DeleteMapping
    public void deleteInvoiceById(@RequestParam ("id") String id){
        invoiceService.deleteInvoiceById(id);
    }


    @PutMapping
    public ResponseEntity<Invoice> updateInvoiceById(@RequestParam ("id") String id, @RequestBody Invoice invoice){
        return invoiceService.updateInvoice(id, invoice);
    }
}
