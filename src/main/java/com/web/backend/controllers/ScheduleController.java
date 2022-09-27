package com.web.backend.controllers;

import com.web.backend.model.job.Schedule;
import com.web.backend.services.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping(params = {"pgSize", "pgNum"})
    public ResponseEntity<Page<Schedule>> getScheduleListPageWithSearch(@RequestParam Integer pgSize,
                                                                        @RequestParam Integer pgNum,
                                                                        @RequestParam(required = false, defaultValue = "") String searchSelect,
                                                                        @RequestParam(required = false, defaultValue = "") String searchString,
                                                                        @RequestParam(required = false, defaultValue = "") String dateSelect,
                                                                        @RequestParam(required = false, defaultValue = "2001-12-05") String date,
                                                                        @RequestParam(required = false, defaultValue = "") String startTimeSelect,
                                                                        @RequestParam(required = false, defaultValue = "00:00") String startTime,
                                                                        @RequestParam(required = false, defaultValue = "") String endTimeSelect,
                                                                        @RequestParam(required = false, defaultValue = "00:00") String endTime,
                                                                        @RequestParam(required = false, defaultValue = "both")String showSelect,
                                                                        @RequestParam(required = false, defaultValue = "desc") String sortDir,
                                                                        @RequestParam(required = false, defaultValue = "date") String sortCol
                                                                        ) {
        log.info("ScheduleController received GET request with params {pgSize, pgNum} and others");
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm")) ;
        LocalTime localEndTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));
        return ResponseEntity.ok(service.getScheduleListPageWithSearch(
                pgSize, pgNum, searchSelect, searchString, dateSelect, localDate,
                startTimeSelect, localStartTime, endTimeSelect, localEndTime,
                showSelect, sortDir, sortCol));
    }

    @PostMapping
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
