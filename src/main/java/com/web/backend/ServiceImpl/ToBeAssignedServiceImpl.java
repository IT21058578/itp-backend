package com.university.university.university.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.university.university.Collections.ToBeAssigned;
import com.university.university.university.Repository.ToBeAssignedRepository;
import com.university.university.university.Service.ToBeAssignedService;

@Service
public class ToBeAssignedServiceImpl implements ToBeAssignedService{

    @Autowired
    ToBeAssignedRepository toBeAssignedRepository;

    @Override
    public String saveToBeAssigned(ToBeAssigned toBeAssigned) {
        toBeAssignedRepository.save(toBeAssigned);
        return null;
    }

    @Override
    public String updateToBeAssigned(String id, ToBeAssigned toBeAssigned) {
        ToBeAssigned toBeAssigned1=toBeAssignedRepository.findById(id).get();
        toBeAssigned1.setDate(toBeAssigned.getDate());
        toBeAssigned1.setTime(toBeAssigned.getTime());
        toBeAssigned1.setZone(toBeAssigned.getZone());
        toBeAssigned1.setAddress(toBeAssigned.getAddress());
        toBeAssigned1.setCrewId(toBeAssigned.getCrewId());
        toBeAssigned1.setCrewMembers(toBeAssigned.getCrewMembers());
        toBeAssignedRepository.save(toBeAssigned1);
        return "Assigned Updated Successfully";
    }

    @Override
    public List<ToBeAssigned> getAll() {
        return toBeAssignedRepository.findAll();
    }

    @Override
    public boolean existsById(String id) {
        if (!(toBeAssignedRepository.existsById(id))) {
			return false;
		}
		return true;
    }

    @Override
    public Optional<ToBeAssigned> getById(String id) {
        return toBeAssignedRepository.findById(id);
    }

    @Override
    public String deleteId(String id) {
        toBeAssignedRepository.deleteById(id);
        return "To be assign Deleted Successfully";
    }
}
