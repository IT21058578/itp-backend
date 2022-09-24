package com.web.backend.services.Booking;

import com.web.backend.exception.NotFoundException;
import com.web.backend.model.booking.booking;
import com.web.backend.repositories.booking.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService {
    private final MongoTemplate template;
    private final BookingRepository repository;

    public List<booking> getBookingList() {
        log.info("Getting all schedule details... ");
        return repository.findAll();
    }
    public void booking(booking booking) {
        log.info("Creating new employee with details: {} ...", booking);
        repository.save(booking);
    }
    public void editBooking(String id, booking booking) {
        log.info("Getting details of schedule with id {} ...", id);
        booking oldbooking = repository.findById(id).orElseThrow(NotFoundException::new);
        log.info("Replacing old details with new details...");
        booking.setId(oldbooking.getId());
        log.info("Saving new schedule details...");
        repository.save(booking);
    }
    public void deleteBooking(String id) {
        log.info("Deleting schedule with id {} ...", id);
        repository.deleteById(id);
    }
}
