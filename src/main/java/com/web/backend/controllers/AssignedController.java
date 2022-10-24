package com.university.university.university.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.university.university.Collections.Assigned;
import com.university.university.university.Service.AssignedService;

@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/assigned")
@RestController
public class AssignedController {

    @Autowired
	private AssignedService assignedService;
	
	@PostMapping("/save")
	public String save(@RequestBody Assigned assigned) {
        if(!assignedService.existsById(assigned.getBookingId()))
		{
		    assignedService.saveAssigned(assigned);
		    return "Booking Assigned Successfully";
        }
		else
		    return "Booking Id Already Exist";
	}

	@PutMapping("/update/{id}")
	public String Update(@PathVariable String id,@RequestBody Assigned assigned) {
		assignedService.updateAssigned(id,assigned);
		return "Booking Assigned Updated Successfully";
	}

	@GetMapping("/getAll")
	public List<Assigned> getAllBook() {
		return assignedService.getAll();
		
	}

	@GetMapping("/getOne/{id}")
	public Optional<Assigned> getByOne(@PathVariable String id) {
		return assignedService.getById(id);
		
	}

	@DeleteMapping("/delete/{id}")
	public String Delete(@PathVariable String id) {
		assignedService.deleteId(id);
		return "Assigned Deleted Successfully";
		
	}
    
}
