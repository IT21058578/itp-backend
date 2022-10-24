package com.university.university.university.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.university.university.Collections.ToBeAssigned;
import com.university.university.university.Service.ToBeAssignedService;

@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/toBeAssigned")
@RestController
public class ToBeAssignedController {

    @Autowired
	private ToBeAssignedService toBeAssignedService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody ToBeAssigned toBeAssigned) {
        if(!toBeAssignedService.existsById(toBeAssigned.getBookingId()))
		{
		    toBeAssignedService.saveToBeAssigned(toBeAssigned);
		    return ResponseEntity.ok(toBeAssigned);
        }
		else
		return ResponseEntity.ok("Booking Id Already Exist");
	}

	@PutMapping("/update/{id}")
	public String Update(@PathVariable String id,@RequestBody ToBeAssigned toBeAssigned) {
		toBeAssignedService.updateToBeAssigned(id,toBeAssigned);
		return "Booking Assigned Updated Successfully";
	}

	@GetMapping("/getAll")
	public List<ToBeAssigned> getAllBook() {
		return toBeAssignedService.getAll();
		
	}
	
	@GetMapping("/getOne/{id}")
	public Optional<ToBeAssigned> getByOne(@PathVariable String id) {
		return toBeAssignedService.getById(id);
		
	}

	@DeleteMapping("/delete/{id}")
	public String Delete(@PathVariable String id) {
		toBeAssignedService.deleteId(id);
		return "To be assign Deleted Successfully";
		
	}
}
