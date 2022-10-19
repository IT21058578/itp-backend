package com.web.backend.model.ServiceCreation;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "HomeCleaning")
public class HomeCleaningModel {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private Long id;
    @Field
    private String name;
    @Field
    private String image;
    @Field
    private String description;
    @Field
    private String CardDescription;
    @Field
    private double price;
    @Field
    private String category;
    @Field
    private String delete;
    @Field
    private String update;


    public HomeCleaningModel() {
    }

    public HomeCleaningModel(String name, String image, String description, String cardDescription, double price, String category, String delete, String update) {
        this.name = name;
        this.image = image;
        this.description = description;
        CardDescription = cardDescription;
        this.price = price;
        this.category = category;
        this.delete = delete;
        this.update = update;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCardDescription() {
        return CardDescription;
    }

    public void setCardDescription(String CardDescription) {
        this.CardDescription = CardDescription;
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

    public String getDelete() {
        delete="deleteHomeC";
        return delete;
    }

    public String getUpdate() {
        update="updateHomeC";
        return update;
    }

    @Override
    public String toString() {
        return "HomeCleaningModel{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", CardDescription='" + CardDescription + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", delete='" + delete + '\'' +
                ", update='" + update + '\'' +
                '}';
    }
}
