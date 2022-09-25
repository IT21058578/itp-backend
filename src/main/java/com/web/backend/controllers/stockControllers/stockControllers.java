package com.web.backend.controllers.stockControllers;

import com.web.backend.model.job.Schedule;
import com.web.backend.model.stock.stock;
import com.web.backend.services.stockServices.stockServices;
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
public class stockControllers {
    private final stockServices service;

    @GetMapping
    public ResponseEntity<List<stock>> getAllStock() {
        log.info("stockController received GET request");
        return ResponseEntity.ok(service.getAllStock());
    }
    @PostMapping
    public ResponseEntity<?> postStock(@RequestBody stock stock) {
        log.info("stockController received POST request with body : {}", stock);
        service.postStock(stock);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<?> editStock(@RequestParam String id, @RequestBody stock stock) {
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
