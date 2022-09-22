package com.web.backend.services;

import com.web.backend.exception.NotFoundException;
import com.web.backend.model.job.Schedule;
import com.web.backend.model.user.AppUser;
import com.web.backend.repositories.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor @Slf4j
public class ScheduleService {
    private final MongoTemplate template;
    private final ScheduleRepository repository;

    public List<Schedule> getScheduleList() {
        log.info("Getting all schedule details... ");
        return repository.findAll();
    }

    public void postSchedule(Schedule schedule) {
        log.info("Creating new employee with details: {} ...", schedule);
        repository.save(schedule);
    }

    public void editSchedule(String id, Schedule schedule) {
        log.info("Getting details of schedule with id {} ...", id);
        Schedule oldSchedule = repository.findById(id).orElseThrow(NotFoundException::new);
        log.info("Replacing old details with new details...");
        schedule.setId(oldSchedule.getId());
        log.info("Saving new schedule details...");
        repository.save(schedule);
    }

    public void deleteSchedule(String id) {
        log.info("Deleting schedule with id {} ...", id);
        repository.deleteById(id);
    }
}
