
package com.atles.test.repositories;

import com.atles.test.model.BasicPriceOfServices;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicPriceRepository extends MongoRepository<BasicPriceOfServices, Long> {

}
