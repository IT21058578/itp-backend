package com.web.backend.services;

import com.web.backend.dto.ZoneSearchSortParams;
import com.web.backend.exception.NotFoundException;
import com.web.backend.model.crewAssignment.Zone;
import com.web.backend.model.job.Schedule;
import com.web.backend.repositories.ZoneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j @AllArgsConstructor
public class ZoneService {
    private final ZoneRepository repository;
    private final MongoTemplate template;

    public Zone getZone(String id) {
        log.info("Getting zone with id {}", id);
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Page<Zone> getZoneList(ZoneSearchSortParams searchParams) {
        log.info("Getting zone page with params : {}", searchParams);
        log.info("Building aggregation pipeline...");
        var pageRequest = PageRequest.of(searchParams.getPgNum(), searchParams.getPgNum());
        var aggregationPipeline = new ArrayList<AggregationOperation>();

        log.info("Modifying pipeline according to sort params...");
        if (!searchParams.getSortCol().isBlank()) {
            switch (searchParams.getSortDir()) {
                case "asc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.ASC, searchParams.getSortCol()));
                case "desc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.DESC, searchParams.getSortCol()));
            }
        }

        log.info("Limiting query according to page params...");
        aggregationPipeline.add(Aggregation.skip((searchParams.getPgNum() - 1) * searchParams.getPgSize()));
        aggregationPipeline.add(Aggregation.limit(searchParams.getPgSize()));

        log.info("Finalizing pipeline...");
        var aggregation = Aggregation.newAggregation(aggregationPipeline);

        log.info("Getting page...");
        var query = new Query().with(pageRequest);
        var zoneList = template.aggregate(aggregation, "Zone" ,Zone.class).getMappedResults();
        long totalZoneCount = template.count(query.limit(0).skip(0), Zone.class);
        var schedulePage = new PageImpl<>( zoneList, pageRequest, totalZoneCount);

        log.info("Returning page...");
        return schedulePage;
    }

    public void putZone(String id, Zone zone) {
        Zone dbZone = getZone(id);

        log.info("Updating zone details...");
        dbZone.setName(zone.getName());
        dbZone.setDescription(zone.getDescription());
        dbZone.setLastUpdatedOn(LocalDate.now());

        log.info("Saving updated zone...");
        repository.save(dbZone);
    }

    public void deleteZone(String id) {
        log.info("Deleting zone with id {}", id);
        Zone dbZone = getZone(id);
        repository.deleteById(id);
    }

    public void postZone(Zone zone) {
        log.info("Creating new zone; {} ", zone);
        zone.setCreatedOn(LocalDate.now());
        zone.setLastUpdatedOn(LocalDate.now());
        repository.save(zone);
    }
}
