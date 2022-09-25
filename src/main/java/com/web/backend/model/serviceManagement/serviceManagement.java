package com.web.backend.model.serviceManagement;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="serviceManagement") @Data

public class serviceManagement {

    private String id;
    private String su_name;
    private String su_contact;
    private String  su_email;
    private String contact;
    private String product_type;
    private Number monthly_due;
}
