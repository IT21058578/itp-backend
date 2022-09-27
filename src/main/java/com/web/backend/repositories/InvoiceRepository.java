package com.web.backend.repositories;

import com.web.backend.model.payment.Invoice;
import com.web.backend.model.user.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {

    Invoice findInvoiceById(String id);
}
