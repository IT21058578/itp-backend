package com.web.backend.services.supplierServices;

import com.web.backend.exception.NotFoundException;
import com.web.backend.model.supplier.supplier;
import com.web.backend.repositories.supplierRepositories.supplierRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class supplierServices {
    private final MongoTemplate template;
    private final supplierRepositories repository;

    public List<supplier> getAllStock() {
        log.info("Getting all stock details... ");
        return repository.findAll();
    }
    public void postStock(supplier stock) {
        log.info("Creating new stock with details: {} ...", stock);
        repository.save(stock);
    }
    public void editStock(String id, supplier stock) {
        log.info("Getting details of schedule with id {} ...", id);
        supplier oldStock = repository.findById(id).orElseThrow(NotFoundException::new);
        log.info("Replacing old details with new details...");
        stock.setId(oldStock.getId());
        log.info("Saving new schedule details...");
        repository.save(stock);
    }
    public void deleteStock(String id) {
        log.info("Deleting schedule with id {} ...", id);
        repository.deleteById(id);
    }


}
