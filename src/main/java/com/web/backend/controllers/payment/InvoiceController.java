package com.web.backend.controllers.payment;

import com.web.backend.model.payment.Invoice;
import com.web.backend.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/invoice")
@AllArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping()
    public List<Invoice> fetchAllInvoices(@RequestParam ("email") String email){
        return invoiceService.findInvoiceByEmail(email);
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
