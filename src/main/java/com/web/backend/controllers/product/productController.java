package com.web.backend.controllers.product;

import com.web.backend.model.job.Schedule;
import com.web.backend.model.product.product;
import com.web.backend.services.ScheduleService;
import com.web.backend.services.product.productService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class productController {
    private final productService service;

    @GetMapping(params = {})
    public ResponseEntity<List<product>> getProductList() {
        log.info("ScheduleController received GET request");
        return ResponseEntity.ok(service.getProductList());
    }

    @PostMapping(params = {})
    public ResponseEntity<?> postProduct(@RequestBody product product) {
        log.info("ScheduleController received POST request with body : {}", product);
        service.postProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> editProduct(@RequestParam String id, @RequestBody product product) {
        log.info("ScheduleController received PUT request with params: {} , body: {}", id, product);
        service.editProduct(id, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestParam String id) {
        log.info("ScheduleController received DELETE request with params: {}", id);
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
