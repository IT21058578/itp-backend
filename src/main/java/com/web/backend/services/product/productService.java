package com.web.backend.services.product;

import com.web.backend.exception.NotFoundException;
import com.web.backend.model.job.Schedule;
import com.web.backend.model.product.product;
import com.web.backend.repositories.ScheduleRepository;
import com.web.backend.repositories.product.productRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class productService {
    private final MongoTemplate template;
    private final productRepository repository;

    public List<product> getProductList() {
        log.info("Getting all product details... ");
        return repository.findAll();
    }
    public void postProduct(product product) {
        log.info("Creating new schedule with details: {} ...", product);
        repository.save(product);
    }

    public void editProduct(String id, product product) {
        log.info("Getting details of schedule with id {} ...", id);
        product oldProduct = repository.findById(id).orElseThrow(NotFoundException::new);
        log.info("Replacing old details with new details...");
        product.setId(oldProduct.getId());
        log.info("Saving new schedule details...");
        repository.save(product);
    }

    public void deleteProduct(String id) {
        log.info("Deleting schedule with id {} ...", id);
        repository.deleteById(id);
    }


}
