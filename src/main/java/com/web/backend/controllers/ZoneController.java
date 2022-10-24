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

import com.university.university.university.Collections.Zones;
import com.university.university.university.Service.ZoneService;

@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/zone")
@RestController
public class ZoneController {
    @Autowired
	private ZoneService zoneService;
	
	@PostMapping("/save")
	public String save(@RequestBody Zones zone) {
		if(!zoneService.existsByArea(zone.getArea()))
		{
			if(!zoneService.existsById(zone.getId()))
			{
				zoneService.saveZone(zone);
				return "Zone Saved Successfully";
			}
			else
				return "Zone Id Already Exist";
		}
		else 
			return "Zone Area Name alredy exist";
		
	}

	@PutMapping("/update/{id}")
	public String Update(@PathVariable String id,@RequestBody Zones zones) {
		zoneService.updateZone(id,zones);
		return "Zone Updated Successfully";
	}

	@DeleteMapping("/delete/{id}")
	public String Delete(@PathVariable String id) {
		zoneService.deleteId(id);
		return "Zone Deleted Successfully";
		
	}

	@GetMapping("/getAll")
	public List<Zones> getAllBook() {
		return zoneService.getAll();
		
	}

	@GetMapping("/getById/{id}")
	public Optional<Zones> getById(@PathVariable String id) {
		return zoneService.getById(id);
		
	}


    
}
