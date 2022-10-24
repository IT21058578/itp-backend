package com.web.backend.model.payment;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document
@Data
public class Instrument {
    @Id
    private String id;
    @Indexed
    private String email;
    @Indexed
    private String cardNumber;
    private String cardType;
    private String cvv;
    private String expiryDate;

    public Instrument(
            String email,
            String cardNumber,
            String cardType,
            String cvv,
            String expiryDate) {
        this.email = email;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }
}
