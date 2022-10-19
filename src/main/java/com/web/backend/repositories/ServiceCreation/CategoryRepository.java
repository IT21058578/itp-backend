package com.web.backend.repositories.ServiceCreation;


import com.web.backend.model.ServiceCreation.CategoryModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository <CategoryModel, Long> {
}
