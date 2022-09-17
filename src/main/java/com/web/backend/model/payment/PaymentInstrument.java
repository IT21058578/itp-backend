package com.web.backend.model.payment;

import com.web.backend.model.user.Client;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class PaymentInstrument {
    @Id
    private String id;
    @Indexed(unique = true)
    private String cardNumber;
    private String cardType;
    private String cardName;
    private Client client;
}
