package com.web.backend.model.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="supplier") @Data

public class supplier {
    private String id;
    private String su_name;
    private String su_contact;
    private String  su_email;
    private String contact;
    private String product_type;
    private Number monthly_due;

    public supplier (String id,String su_name,String  su_contact,String su_email,
                    Number monthly_due,String contact,String product_type){
        this.id =id;
        this.su_name =su_name;
        this.su_contact =su_contact;
        this.su_email =su_email;
        this.monthly_due =monthly_due;
        this.contact =contact;
        this.product_type =product_type;
    }
}
