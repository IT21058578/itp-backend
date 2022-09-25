package com.web.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Document(collection = "laundry")
public class LaundryServices{

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private long id;
    @Field
    private String name;
    @Field
    private double total;

    public LaundryServices() {
    }

    public LaundryServices(String name, double total) {
        this.name = name;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
