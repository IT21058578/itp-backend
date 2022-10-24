package com.university.university.university.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.university.university.Collections.Crews;
import com.university.university.university.Service.CrewsService;

@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/crews")
@RestController
public class CrewsController {
    @Autowired
    private CrewsService crewsService;
	
	@PostMapping("/save")
	public String save(@RequestBody Crews crews) {
		if(!crewsService.existsByEmployeeId(crews.getEmployeeId()))
		{
			if(!crewsService.existsById(crews.getId()))
			{
				crewsService.saveCrews(crews);
				return "Crews Saved Successfully";
			}
			else
				return "Crews id already exist";
		}
		else
			return "Employee ID already exist";
		
	}

	@GetMapping("/getAll")
	public List<Crews> getAllCrews() {
		return crewsService.getAll();
		
	}

	@GetMapping("/getById/{id}")
	public Optional<Crews> getById(@PathVariable String id) {
		return crewsService.getById(id);
		
	}

	@DeleteMapping("/delete/{id}")
	public String Delete(@PathVariable String id) {
		crewsService.deleteId(id);
		return "Crew Deleted Successfully";
		
	}

    
}
