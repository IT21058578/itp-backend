package com.web.backend.repositories.payment;

import com.web.backend.model.payment.Invoice;
import com.web.backend.model.user.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {
    Invoice findInvoiceById(String id);
    List<Invoice> findInvoiceByEmail(String email);


}
