package com.web.backend.model.stocks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="stocks") @Data
public class stocks {
    private String id;
    private String su_name;
    private String su_contact;
    private String  su_email;
    private String product_type;
    private Number monthly_due;
    public stocks(String id, String su_name, String  su_contact, String su_email,
                    Number monthly_due, String contact, String product_type){
        this.id =id;
        this.su_name =su_name;
        this.su_contact =su_contact;
        this.su_email =su_email;
        this.monthly_due =monthly_due;
        this.product_type =product_type;
    }
}
