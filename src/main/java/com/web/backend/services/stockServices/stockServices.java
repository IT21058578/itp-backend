package com.web.backend.services.stockServices;

import com.web.backend.exception.NotFoundException;
import com.web.backend.model.job.Schedule;
import com.web.backend.model.stock.stock;
import com.web.backend.repositories.ScheduleRepository;
import com.web.backend.repositories.stockRepositories.stockRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class stockServices {
    private final MongoTemplate template;
    private final stockRepositories repository;

    public List<stock> getAllStock() {
        log.info("Getting all stock details... ");
        return repository.findAll();
    }
    public void postStock(stock stock) {
        log.info("Creating new stock with details: {} ...", stock);
        repository.save(stock);
    }
    public void editStock(String id, stock stock) {
        log.info("Getting details of schedule with id {} ...", id);
        stock oldStock = repository.findById(id).orElseThrow(NotFoundException::new);
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
