package com.university.university.university.Service;

import java.util.List;
import java.util.Optional;

import com.university.university.university.Collections.Crews;

public interface CrewsService {
    public String saveCrews(Crews crews);
    public List<Crews> getAll();
    public Optional<Crews> getById(String id);
    public boolean existsByEmployeeId(String employeeId);
    public boolean existsById(String id);
    public String deleteId(String id);
}
