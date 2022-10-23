package com.university.university.university.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.university.university.Collections.Assigned;
import com.university.university.university.Repository.AssignedRepository;
import com.university.university.university.Service.AssignedService;

@Service
public class AssignedServiceImpl implements AssignedService{

    @Autowired
    AssignedRepository assignedRepository;

    @Override
    public String saveAssigned(Assigned assigned) {
        assignedRepository.save(assigned);
        return null;
    }
    @Override
    public String updateAssigned(String id, Assigned assigned) {
        Assigned assigned1=assignedRepository.findById(id).get();
        assigned1.setDate(assigned.getDate());
        assigned1.setTime(assigned.getTime());
        assigned1.setZone(assigned.getZone());
        assigned1.setAddress(assigned.getAddress());
        assigned1.setCrewId(assigned.getCrewId());
        assigned1.setCrewMembers(assigned.getCrewMembers());
        assignedRepository.save(assigned1);
        return "Assigned Updated Successfully";
    }

    @Override
    public List<Assigned> getAll() {
        return assignedRepository.findAll();
    }

    @Override
    public boolean existsById(String id) {
        if (!(assignedRepository.existsById(id))) {
			return false;
		}
		return true;
    }
    @Override
    public Optional<Assigned> getById(String id) {
        return assignedRepository.findById(id);
    }

    @Override
    public String deleteId(String id) {
        assignedRepository.deleteById(id);
        return "Assigned Deleted Successfully";
    }
    
}
