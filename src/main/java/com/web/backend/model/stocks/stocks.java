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
    private String productID;
    private String productType;
    private String availableStock;
    private String  NextPurchaseDate;
    private String  test;

    public stocks(String id, String productID, String  productType, String availableStock,
                     String NextPurchaseDate){
        this.id =id;
        this.productID =productID;
        this.productType =productType;
        this.availableStock =availableStock;
        this.NextPurchaseDate =NextPurchaseDate;
    }
}
