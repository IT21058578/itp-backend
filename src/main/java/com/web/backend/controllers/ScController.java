package com.atles.test.controller;

import com.atles.test.model.SC;

import com.atles.test.repositories.ScRepository;
import com.atles.test.services.SqGenarator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ScController {

    private final ScRepository scRepo;

    @Autowired
    public ScController(ScRepository scRepo) {
        this.scRepo = scRepo;
    }

    @Autowired
    private SqGenarator sq;

    @PostMapping("/SC")
    public String saveCate (@RequestBody SC sc){
        sc.setId(sq.getSequenceNumber(SC.SEQUENCE_NAME));
        scRepo.save(sc);
        return "Added Category with Id : "+sc.getId();
    }
    @GetMapping("/findAllCategories")
    public List<SC> getAllCate(){
        return scRepo.findAll();
    }

    @GetMapping("/findCategory/{Id}")
    public Optional<SC> getCate(@PathVariable Long Id){
        return scRepo.findById(Id);
    }

    @DeleteMapping("/deleteCate/{Id}")
    public String deleteCategory(@PathVariable Long Id){
        scRepo.deleteById(Id);
        return "Category was deleted by id : "+ Id;
    }

    @PutMapping("/update/{id}")
    public SC update(@RequestBody SC newSC, @PathVariable Long id) {

        return scRepo.findById(id)
                .map(updateSc -> {
                    updateSc.setName(newSC.getName());
                    updateSc.setDescription(newSC.getDescription());
                    return scRepo.save(updateSc);
                })
                .orElseGet(() -> {
                    newSC.setId(id);
                    return scRepo.save(newSC);
                });
    }
    


}
