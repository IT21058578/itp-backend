package com.web.backend.repositories;

import com.web.backend.model.BasicPriceOfServices;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicPriceRepository extends MongoRepository<BasicPriceOfServices, Long> {

}
