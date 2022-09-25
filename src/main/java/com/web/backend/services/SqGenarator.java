package com.web.backend.services;

import com.web.backend.model.DbSequence;
import static org.springframework.data.mongodb.core.query.Query.query;
import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class SqGenarator {
    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    public SqGenarator(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Long getSequenceNumber(String sequenceName) {
        DbSequence counter = mongoOperations.findAndModify(query(where("_id").is(sequenceName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
