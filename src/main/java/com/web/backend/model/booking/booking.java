package com.web.backend.model.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="booking") @Data

public class booking {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private String  crew;
    private String serviseType;
    private Number payment;
    private Boolean isComplete;

    public booking (LocalDate date,LocalTime time,String  crew,String serviseType,
                    Number payment,Boolean isComplete){
        this.date =date;
        this.time =time;
        this.crew =crew;
        this.serviseType =serviseType;
        this.payment =payment;
        this.isComplete =isComplete;
    }
}

