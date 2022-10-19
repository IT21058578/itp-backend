package com.web.backend.controllers.ServiceCreation;

import com.web.backend.model.ServiceCreation.CategoryModel;
import com.web.backend.model.ServiceCreation.ServiceRequestModel;
import com.web.backend.repositories.ServiceCreation.ServiceRequestRepo;
import com.web.backend.services.ServiceCreation.ServiceRequestSeqGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceRequestController {

    private final ServiceRequestRepo serviceReqRepo;

    @Autowired
    public ServiceRequestController(ServiceRequestRepo serviceReqRepo) {
        this.serviceReqRepo = serviceReqRepo;
    }

    @Autowired
    private ServiceRequestSeqGen sq;

    @PostMapping("/serviceReq")
    public String ServiceRequest (@RequestBody ServiceRequestModel sr){
        sr.setId(sq.getSequenceNumber(ServiceRequestModel.SEQUENCE_NAME));
        serviceReqRepo.save(sr);
        return "Added Category with Id : "+sr.getId();
    }

    @GetMapping("/findAllRequestedServices")
    public List<ServiceRequestModel> getAllRequestedServices(){
        return serviceReqRepo.findAll();
    }

    @GetMapping("/findRequestedService/{Id}")
    public Optional<ServiceRequestModel> getRequestedServices(@PathVariable Long Id){
        return serviceReqRepo.findById(Id);
    }

    @DeleteMapping("/deleteRequestedService/{Id}")
    public String deleteRequestedServices(@PathVariable Long Id){
        serviceReqRepo.deleteById(Id);
        return "Home Apartment Cleaning was deleted by id : "+ Id;
    }
}
