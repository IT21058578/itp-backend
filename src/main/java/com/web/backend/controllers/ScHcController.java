package com.web.backend.controllers;
//Service creation - Home cleaning control class
import com.web.backend.model.HomeCleaning;
import com.web.backend.repositories.ScHcRepo;
import com.web.backend.services.HcSqGenarator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ScHcController {

    private final ScHcRepo hcRepo;

    @Autowired
    public ScHcController(ScHcRepo hcRepo){
        this.hcRepo = hcRepo;

    }

    @Autowired
    private HcSqGenarator HcSq;

    @PostMapping("/HC")
    public String HcBooking (@RequestBody HomeCleaning scHc){
        scHc.setId(HcSq.getSequenceNumber(HomeCleaning.SEQUENCE_NAME));
        hcRepo.save(scHc);
        return "Added Category with Id : "+scHc.getId();
    }

    @GetMapping("/findAllHc")
    public List<HomeCleaning> getAllCate(){
        return hcRepo.findAll();
    }
}
