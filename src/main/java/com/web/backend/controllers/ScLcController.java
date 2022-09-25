package com.web.backend.controllers;import com.web.backend.model.

import com.web.backend.model.HomeCleaning;
import com.web.backend.model.LaundryServices;
import com.web.backend.repositories.ScLcRepo;
import com.web.backend.services.HcSqGenarator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ScLcController {

    private final ScLcRepo lcRepo;

    @Autowired
    public ScLcController(ScLcRepo lcRepo){
        this.lcRepo = lcRepo;

    }

    @Autowired
    private HcSqGenarator LcSq;

    @PostMapping("/LC")
    public String LcBooking (@RequestBody LaundryServices ScLc){
        ScLc.setId(LcSq.getSequenceNumber(HomeCleaning.SEQUENCE_NAME));
        lcRepo.save(ScLc);
        return "Added Category with Id : "+ScLc.getId();
    }

    @GetMapping("/findAllLc")
    public List<LaundryServices> getAllLs(){
        return lcRepo.findAll();
    }
}
