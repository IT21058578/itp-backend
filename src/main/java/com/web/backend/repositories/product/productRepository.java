package com.web.backend.repositories.product;

import com.web.backend.model.job.Schedule;
import com.web.backend.model.product.product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends MongoRepository<product, String> {
}
