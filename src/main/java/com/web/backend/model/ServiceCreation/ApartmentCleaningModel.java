package com.web.backend.model.ServiceCreation;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ApartmentCleaning")
public class ApartmentCleaningModel {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private Long id;
    @Field
    private String name;
    @Field
    private String image;
    @Field
    private String CardDescription;

    @Field
    private String description;
    @Field
    private double price;
    @Field
    private String category;


    public ApartmentCleaningModel() {
    }

    public ApartmentCleaningModel(String name, String image, String CardDescription,
                                  String description,
                                  double price,
                                  String category) {
        this.name = name;
        this.image = image;
        this.CardDescription = CardDescription;
        this.category = category;
        this.description = description;
        this.price = price;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCardDescription() {
        return CardDescription;
    }

    public void setCardDescription(String CardDescription) {
        this.CardDescription = CardDescription;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ApartmentCleaningModel{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", CardDescription='" + CardDescription + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
