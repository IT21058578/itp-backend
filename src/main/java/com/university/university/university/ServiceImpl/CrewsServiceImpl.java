package com.university.university.university.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.university.university.Collections.Crews;
import com.university.university.university.Repository.CrewsRepository;
import com.university.university.university.Service.CrewsService;

@Service
public class CrewsServiceImpl implements CrewsService {

    @Autowired
    CrewsRepository crewsRepository;

    @Override
    public String saveCrews(Crews crews) {
        crewsRepository.save(crews);
        return "Crews Saved Successfully";
    }

    @Override
    public List<Crews> getAll() {
        return crewsRepository.findAll();
    }

    @Override
    public boolean existsByEmployeeId(String employeeId) {
        if (!(crewsRepository.existsByEmployeeId(employeeId))) {
			return false;
		}
		return true;
    }

    @Override
    public boolean existsById(String id) {
        if (!(crewsRepository.existsById(id))) {
			return false;
		}
		return true;
    }

    @Override
    public Optional<Crews> getById(String id) {
        return crewsRepository.findById(id);
    }

    @Override
    public String deleteId(String id) {
        crewsRepository.deleteById(id);
        return "Crews Deleted Successfully";
    }
    

}
