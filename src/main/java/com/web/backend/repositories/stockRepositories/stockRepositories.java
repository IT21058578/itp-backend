package com.web.backend.repositories.stockRepositories;
import com.web.backend.model.stock.stock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface stockRepositories extends MongoRepository<stock, String> {
}


