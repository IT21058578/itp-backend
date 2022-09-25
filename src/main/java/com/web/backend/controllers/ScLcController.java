package com.atles.test.controller;

import com.atles.test.model.HomeCleaning;
import com.atles.test.model.LaundryServices;
import com.atles.test.repositories.ScLcRepo;
import com.atles.test.services.HcSqGenarator;
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
