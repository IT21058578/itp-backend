package com.web.backend.services.stocksService;

import com.web.backend.exception.NotFoundException;
import com.web.backend.model.stocks.stocks;
import com.web.backend.repositories.stocksRepositories.stocksRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class stocksServices {
    private final MongoTemplate template;
    private final stocksRepositories repository;

    public List<stocks> getAllStock() {
        log.info("Getting all stock details... ");
        return repository.findAll();
    }
    public void postStock(stocks stock) {
        log.info("Creating new stock with details: {} ...", stock);
        repository.save(stock);
    }
    public void editStock(String id, stocks stock) {
        log.info("Getting details of schedule with id {} ...", id);
        stocks oldStock = repository.findById(id).orElseThrow(NotFoundException::new);
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
