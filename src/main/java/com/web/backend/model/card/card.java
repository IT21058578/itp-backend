package com.web.backend.model.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="card") @Data
public class card {
    private String id;
//    private String address;
//    private String serviceName;
//    private Number Qty;
//    private Number unitPrice;
//    private String customerName;
//    private String email;
//    private String contact;

        private String name;
    private String image;
    private String category;
    private Number price;
private  String cardDescription;
//    public card(String address, String serviceName, Number Qty,
//                Number unitPrice, String customerName, String email,
//                String contact) {
//        this.address = address;
//        this.serviceName = serviceName;
//        this.customerName = customerName;
//        this.email = email;
//        this.Qty = Qty;
//        this.unitPrice = unitPrice;
//        this.contact = contact;
//    }
public card(String name, String image, String category,
            Number price,String cardDescription) {
    this.name = name;
    this.image = image;
    this.category = category;
    this.price = price;
    this.cardDescription =cardDescription;
}
}
