package com.web.backend.controllers.booking;

import com.web.backend.model.booking.booking;
import com.web.backend.services.Booking.BookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/booking")
@AllArgsConstructor @CrossOrigin @Slf4j
@Service

public class BookingController {
    private final BookingService service;

    @GetMapping
    public ResponseEntity<List<booking>> getBooking() {
        log.info("ScheduleController received GET request");
        return ResponseEntity.ok(service.getBookingList());
    }
    @PostMapping
    public ResponseEntity<?> postBooking(@RequestBody booking booking) {
        log.info("ScheduleController received POST request with body : {}", booking);

        service.booking(booking);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> editBooking(@RequestParam String id, @RequestBody booking booking) {
        log.info("ScheduleController received PUT request with params: {} , body: {}", id, booking);
        service.editBooking(id, booking);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBooking(@RequestParam String id) {
        log.info("ScheduleController received DELETE request with params: {}", id);
        service.deleteBooking(id);
        return ResponseEntity.ok().build();
    }
}
