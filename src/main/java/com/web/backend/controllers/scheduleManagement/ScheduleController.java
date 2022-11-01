package com.web.backend.controllers.scheduleManagement;

import com.web.backend.dto.schedManagement.ScheduleSearchSortParameters;
import com.web.backend.model.job.Schedule;
import com.web.backend.services.scheduleManagement.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/schedule")
@AllArgsConstructor @CrossOrigin @Slf4j
public class ScheduleController {
    private final ScheduleService service;

    @GetMapping(params = {})
    public ResponseEntity<List<Schedule>> getScheduleList() {
        log.info("ScheduleController received GET request");
        return ResponseEntity.ok(service.getScheduleList());
    }


    public ResponseEntity<Page<Schedule>> getScheduleListPage(
            @RequestParam Integer pgSize, @RequestParam Integer pgNum) {
        log.info("ScheduleController received GET request with params {pgSize, pgNum}");
        return ResponseEntity.ok(service.getScheduleListPage(pgSize, pgNum));
    }

    @PostMapping(value = "/search")
    public ResponseEntity<Page<Schedule>> getScheduleListPageWithSearch(@RequestBody ScheduleSearchSortParameters searchParams) {
        log.info("ScheduleController received GET request with params {pgSize, pgNum} and others");
        return ResponseEntity.ok(service.getScheduleListPageWithSearch(searchParams));
    }

    @PostMapping(params = {})
    public ResponseEntity<?> postSchedule(@RequestBody Schedule schedule) {
        log.info("ScheduleController received POST request with body : {}", schedule);
        service.postSchedule(schedule);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> editSchedule(@RequestParam String id, @RequestBody Schedule schedule) {
        log.info("ScheduleController received PUT request with params: {} , body: {}", id, schedule);
        service.editSchedule(id, schedule);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteSchedule(@RequestParam String id) {
        log.info("ScheduleController received DELETE request with params: {}", id);
        service.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }
}
