package com.web.backend.services.addressManagement;

import com.web.backend.exception.NotFoundException;
import com.web.backend.model.addressManagement.addressManagement;
import com.web.backend.repositories.addressManagement.addressRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class addressManagementService {
    private final MongoTemplate template;
    private final addressRepository repository;

    public List<addressManagement> getAddressList() {
        log.info("Getting all schedule details... ");
        return repository.findAll();
    }

    public void addressPost(addressManagement addressManagement) {
        log.info("Creating new employee with details: {} ...", addressManagement);
        repository.save(addressManagement);
    }
    public void editAddress(String id, addressManagement addressManagement) {
        log.info("Getting details of schedule with id {} ...", id);
        addressManagement oldAddress = repository.findById(id).orElseThrow(NotFoundException::new);
        log.info("Replacing old details with new details...");
        addressManagement.setId(oldAddress.getId());
        log.info("Saving new schedule details...");
        repository.save(addressManagement);
    }
    public void deleteAddress(String id) {
        log.info("Deleting schedule with id {} ...", id);
        repository.deleteById(id);
    }
}
