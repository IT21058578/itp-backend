package com.web.backend.webcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.backend.model.payment.Invoice;
import com.web.backend.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class InvoiceWebServiceController {

    @Autowired
    private final InvoiceService invoiceService;


    public InvoiceWebServiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path="/invoice")
    //public List<Invoice> getInvoices(@RequestParam(value="client", required = false) String client)
    public Iterable<Invoice> getInvoices(){
        return this.invoiceService.getAllInvoice();
    }

    @PostMapping(value = "/invoice/save")
    public ResponseEntity<?> saveOrUpdateInvoice(@RequestBody Invoice invoice){
        invoiceService.saveOrUpdateInvoice(invoice);
        return new ResponseEntity<>("Invoice Added Successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/invoice/delete/{invoiceId}")
    public ResponseEntity<?> deleteInvoiceByInvoiceId(@PathVariable String invoiceId){
        invoiceService.deleteInvoiceById(invoiceService.findInvoiceById(invoiceId).getId());
        return new ResponseEntity<>("Invoice deleted Successfully", HttpStatus.OK);
    }
}
