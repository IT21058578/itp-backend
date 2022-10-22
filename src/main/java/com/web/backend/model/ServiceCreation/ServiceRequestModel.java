package com.web.backend.model.ServiceCreation;

        import org.springframework.data.annotation.Id;
        import org.springframework.data.annotation.Transient;
        import org.springframework.data.mongodb.core.mapping.Document;
        import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ServiceRequest")
public class ServiceRequestModel {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private Long id;
    @Field
    private String name;
    @Field
    private String description;

    public ServiceRequestModel() {
    }

    public ServiceRequestModel(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "ServiceRequestModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

