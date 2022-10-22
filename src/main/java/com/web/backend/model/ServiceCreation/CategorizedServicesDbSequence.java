package com.web.backend.model.ServiceCreation;

        import org.springframework.data.annotation.Id;
        import org.springframework.data.mongodb.core.mapping.Document;
        import org.springframework.data.mongodb.core.mapping.Field;
        import org.springframework.stereotype.Component;

@Document(collection = "categorizedServicesDbSq")
@Component
public class CategorizedServicesDbSequence {
    @Id
    private String  id;
    @Field
    private Long seq;

    public CategorizedServicesDbSequence() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}

