package com.web.backend.model.addressManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="addressManagement") @Data

public class addressManagement {
    private  String id;
    private String cu_name;
    private String cu_address;
    private String cu_city;
    private String cu_country;
    private String cu_mobile;

    public addressManagement (String cu_name,String cu_address,String cu_city,
                              String cu_country,String cu_mobile){
        this.cu_name =cu_name;
        this.cu_address =cu_address;
        this.cu_city =cu_city;
        this.cu_country =cu_country;
        this.cu_mobile =cu_mobile;
    }

}
