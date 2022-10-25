package com.web.backend.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="product") @Data
public class product {
    private String id;
    private String productId;
    private String productType;
    private String nextPurchaseDate;
    private String availabelStock;
    private String supplierId;

    public product(String productId, String productType,
                   String nextPurchaseDate,
                   String availabelStock,
                   String supplierId) {
        this.productId = productId;
        this.productType = productType;
        this.nextPurchaseDate = nextPurchaseDate;
        this.availabelStock = availabelStock;
        this.supplierId = supplierId;
    }
}
