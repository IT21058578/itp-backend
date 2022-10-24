package com.university.university.university.Service;

import java.util.List;
import java.util.Optional;

import com.university.university.university.Collections.Assigned;

public interface AssignedService {
    public String saveAssigned(Assigned assigned);
    public String updateAssigned(String id,Assigned assigned);
    public List<Assigned> getAll();
    public Optional<Assigned> getById(String id);
    public boolean existsById(String id);
    public String deleteId(String id);
}
