package com.web.backend.services.ServiceCreation;

import com.web.backend.model.ServiceCreation.CategorizedServicesDbSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class CategorizedServicesSequenceGenerator {
    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    public CategorizedServicesSequenceGenerator(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Long getSequenceNumber(String sequenceName) {
        CategorizedServicesDbSequence counter = mongoOperations.findAndModify(query(where("_id").is(sequenceName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                CategorizedServicesDbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}

