package com.web.backend.model.stock;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="stock") @Data

public class stock {
    private String id;
    private String su_name;
    private String su_contact;
    private String  su_email;
    private String product_type;
    private Number monthly_due;
}
