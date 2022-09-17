package com.web.backend.model.payment;

import com.web.backend.model.user.Client;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Payment {
    @Id
    private String id;
    private Double amount;
    private Client client;
    private PaymentInstrument paymentInstrument;
    private boolean paymentStatus;

}
