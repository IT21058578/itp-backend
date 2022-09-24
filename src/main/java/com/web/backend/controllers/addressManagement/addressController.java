package com.web.backend.controllers.addressManagement;

import com.web.backend.model.addressManagement.addressManagement;
import com.web.backend.services.addressManagement.addressManagementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class addressController {
    private final addressManagementService service;

    @GetMapping
    public ResponseEntity<List<addressManagement>> getAddress() {
        log.info("Address Controller received GET request");
        return ResponseEntity.ok(service.getAddressList());
    }

    @PostMapping
    public ResponseEntity<?> postSchedule(@RequestBody addressManagement addressManagement) {
        log.info("ScheduleController received POST request with body : {}", addressManagement);

        service.addressPost(addressManagement);
        return ResponseEntity.ok(new String[]{"sucess"});
    }

    @PutMapping
    public ResponseEntity<?> editAddress(@RequestParam String id, @RequestBody addressManagement addressManagement) {
        log.info("ScheduleController received PUT request with params: {} , body: {}", id, addressManagement);
        service.editAddress(id, addressManagement);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAddress(@RequestParam String id) {
        log.info("ScheduleController received DELETE request with params: {}", id);
        service.deleteAddress(id);
        return ResponseEntity.ok().build();
    }

}
