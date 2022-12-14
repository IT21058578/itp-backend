package com.web.backend.controllers.crewAssignment;

import com.web.backend.dto.crewAssignment.ZoneEmployeeSearchSortParams;
import com.web.backend.dto.crewAssignment.ZoneSearchSortParams;
import com.web.backend.model.crewAssignment.Zone;
import com.web.backend.services.crewAssignment.ZoneService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/zone") @RestController @Slf4j
@AllArgsConstructor @CrossOrigin
public class ZoneController {
    private final ZoneService service;

    @PostMapping
    public ResponseEntity<?> postZone(@RequestBody Zone zone) {
        log.info("ZoneController received POST request; {}", zone);
        service.postZone(zone);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<?> deleteZone(@RequestParam String id) {
        log.info("ZoneController received DELETE request with id {}", id);
        service.deleteZone(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/toggle", params = {"id", "disable"})
    public ResponseEntity<?> toggleZoneDisable(@RequestParam String id, @RequestParam Boolean disable) {
        log.info("ZoneController received PUT request to toggle disabled status for {} to {}", id, disable);
        service.toggleZoneDisable(id, disable);
        return ResponseEntity.ok().build();
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<?> putZone(@RequestBody Zone zone, @RequestParam String id) {
        log.info("ZoneController received PUT request with id {} ; {}", id, zone);
        service.putZone(id, zone);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<?> getZone(@RequestParam String id) {
        log.info("ZoneController received GET request with id {}", id);
        return ResponseEntity.ok(service.getZone(id));
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> getZoneList(ZoneSearchSortParams searchParams) {
        log.info("ZoneController received POST request for zones with paging");
        return ResponseEntity.ok(service.getZoneList(searchParams));
    }
}
