package com.web.backend.controllers.stocksController;

import com.web.backend.model.stocks.stocks;
import com.web.backend.services.stocksService.stocksServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class stocksController {
    private final stocksServices service;

    @GetMapping
    public ResponseEntity<List<stocks>> getAllStock() {
        log.info("stockController received GET request");
        return ResponseEntity.ok(service.getAllStock());
    }
    @PostMapping
    public ResponseEntity<?> postStock(@RequestBody stocks stock) {
        log.info("stockController received POST request with body : {}", stock);
        service.postStock(stock);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<?> editStock(@RequestParam String id, @RequestBody stocks stock) {
        log.info("stockController received PUT request with params: {} , body: {}", id, stock);
        service.editStock(id, stock);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<?> deleteStock(@RequestParam String id) {
        log.info("stockController received DELETE request with params: {}", id);
        service.deleteStock(id);
        return ResponseEntity.ok().build();
    }
}
