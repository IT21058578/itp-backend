package com.web.backend.services.crewAssignment;

import com.web.backend.dto.crewAssignment.ZoneEmployeeSearchSortParams;
import com.web.backend.dto.crewAssignment.ZoneJobSearchSortParams;
import com.web.backend.dto.crewAssignment.ZoneSearchSortParams;
import com.web.backend.exception.AlreadyExistsException;
import com.web.backend.exception.NotFoundException;
import com.web.backend.model.crewAssignment.Employee;
import com.web.backend.model.crewAssignment.Zone;
import com.web.backend.model.job.Job;
import com.web.backend.repositories.crewAssignment.ZoneRepository;
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
        var pageRequest = PageRequest.of(searchParams.getPgNum(), searchParams.getPgSize());
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
        var zoneList = template.aggregate(aggregation, "zone" ,Zone.class).getMappedResults();
        long totalZoneCount = template.count(query.limit(0).skip(0), Zone.class);
        var zonePage = new PageImpl<>( zoneList, pageRequest, totalZoneCount);

        log.info("Returning page...");
        return zonePage;
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
        if (repository.getZoneBySign(zone.getSign()).isPresent()) {
            throw new AlreadyExistsException("Zone with specified sign already exists");
        }

        zone.setCreatedOn(LocalDate.now());
        zone.setLastUpdatedOn(LocalDate.now());
        repository.save(zone);
    }

    public Page<Employee> getZoneEmployeeList(ZoneEmployeeSearchSortParams searchParams) {
        log.info("Getting employee page with params : {}", searchParams);

        log.info("Building aggregation pipeline...");
        var pageRequest = PageRequest.of(searchParams.getPgNum(), searchParams.getPgNum());
        var aggregationPipeline = new ArrayList<AggregationOperation>();

        log.info("Modifying pipeline according to search params...");
        aggregationPipeline.add(Aggregation.match(Criteria.where("zoneAssignmentsList.id").regex(searchParams.getId())));

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
        var employeeList = template.aggregate(aggregation, "Employee" ,Employee.class).getMappedResults();
        long totalCount = template.count(query.limit(0).skip(0), Employee.class ); //TODO: This makes the code highly inefficient. Also incompatible with match operations.
        var employeePage = new PageImpl<>(employeeList, pageRequest, totalCount);

        log.info("Returning page...");
        return employeePage;
    }

    public Page<Job> getZoneJobList(ZoneJobSearchSortParams searchParams) {
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

        log.info("Modifying pipeline according to search params...");
        aggregationPipeline.add(Aggregation.match(Criteria.where("zoneId").is(searchParams.getId())));

        log.info("Limiting query according to page params...");
        aggregationPipeline.add(Aggregation.skip((searchParams.getPgNum() - 1) * searchParams.getPgSize()));
        aggregationPipeline.add(Aggregation.limit(searchParams.getPgSize()));

        log.info("Finalizing pipeline...");
        var aggregation = Aggregation.newAggregation(aggregationPipeline);

        log.info("Getting page...");
        var query = new Query().with(pageRequest);
        var jobList = template.aggregate(aggregation, "Job" , Job.class).getMappedResults();
        long totalCount = template.count(query.limit(0).skip(0), Job.class ); //TODO: This makes the code highly inefficient. Also incompatible with match operations.
        var jobPage = new PageImpl<>(jobList, pageRequest, totalCount);

        log.info("Returning page...");
        return jobPage;
    }
}
