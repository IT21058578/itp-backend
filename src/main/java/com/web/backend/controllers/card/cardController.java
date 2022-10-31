package com.web.backend.controllers.card;


import com.web.backend.model.card.card;
import com.web.backend.model.job.Schedule;
import com.web.backend.services.ScheduleService;
import com.web.backend.services.card.cardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class cardController {
    private final cardService service;

    @GetMapping(params = {})
    public ResponseEntity<List<card>> getCardList() {
        log.info("ScheduleController received GET request");
        return ResponseEntity.ok(service.getCardList());
    }

    @PostMapping(params = {})
    public ResponseEntity<?> postCard(@RequestBody card card) {
        log.info("ScheduleController received POST request with body : {}", card);
        service.postCard(card);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> editCard(@RequestParam String id, @RequestBody card card) {
        log.info("ScheduleController received PUT request with params: {} , body: {}", id, card);
        service.editCard(id, card);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<?> deleteCard(@RequestParam String id) {
        log.info("ScheduleController received DELETE request with params: {}", id);
        service.deleteCard(id);
        return ResponseEntity.ok().build();
    }
}
