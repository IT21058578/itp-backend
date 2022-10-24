package com.web.backend.model.payment;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Payment {
    @Id
    private String id;
    private Double amount;
    @Indexed
    private String email;
    @Indexed
    private String cardNumber;
    @Indexed
    private String invoiceId;
    private boolean paymentStatus;

    public Payment(Double amount, String email, String cardNumber, String invoiceId, boolean paymentStatus) {
        this.amount = amount;
        this.email = email;
        this.cardNumber = cardNumber;
        this.invoiceId = invoiceId;
        this.paymentStatus = paymentStatus;
    }
}
