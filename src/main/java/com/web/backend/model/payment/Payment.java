package com.web.backend.model.payment;

import com.web.backend.model.user.Client;
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
    private boolean paymentStatus;

}
