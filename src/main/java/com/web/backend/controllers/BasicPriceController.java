package com.atles.test.controller;
import com.atles.test.model.BasicPriceOfServices;
import com.atles.test.model.SC;
import com.atles.test.repositories.BasicPriceRepository;
import com.atles.test.services.SqGenarator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BasicPriceController {

    private final BasicPriceRepository bpRepo;

    public BasicPriceController(BasicPriceRepository bpRepo) {
        this.bpRepo = bpRepo;
    }

    @Autowired
    private SqGenarator sq;

    @PostMapping("/BP")
    public String saveCate (@RequestBody BasicPriceOfServices bp){
        //bp.setId(sq.getSequenceNumber(SC.SEQUENCE_NAME));
        bpRepo.save(bp);
        return "Added Prices : ";
    }

    @GetMapping("/getHcBasicPrices")
    public List<BasicPriceOfServices> getAllPrices(){
        return bpRepo.findAll();
    }



}
