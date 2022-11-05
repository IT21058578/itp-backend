package com.web.backend.repositories.stocksRepositories;

import com.web.backend.model.stocks.stocks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface stocksRepositories extends MongoRepository<stocks, String> {
}
