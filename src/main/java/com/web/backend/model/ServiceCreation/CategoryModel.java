package com.web.backend.model.ServiceCreation;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "CategoryModel")
public class CategoryModel {

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
        private String link;

        public CategoryModel() {
        }

        public CategoryModel(String name, String image, String description, String link) {
            this.name = name;
            this.image = image;
            this.description = description;
            this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
