package com.atles.test.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "HomeCleaning")
public class HomeCleaning{
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private Long id;

    @Field
    private String name;

    @Field
    private double total;

    public HomeCleaning() {}

    public HomeCleaning(String name, double total) {
        this.name = name;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
