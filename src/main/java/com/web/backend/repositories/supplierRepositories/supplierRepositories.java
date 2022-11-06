package com.web.backend.repositories.supplierRepositories;
import com.web.backend.model.supplier.supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface supplierRepositories extends MongoRepository<supplier, String> {
}


