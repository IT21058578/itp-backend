package com.university.university.university.Service;

import java.util.List;
import java.util.Optional;

import com.university.university.university.Collections.ToBeAssigned;

public interface ToBeAssignedService {
    public String saveToBeAssigned(ToBeAssigned toBeAssigned);
    public String updateToBeAssigned(String id,ToBeAssigned toBeAssigned);
    public Optional<ToBeAssigned> getById(String id);
    public List<ToBeAssigned> getAll();
    public boolean existsById(String id);
    public String deleteId(String id);
}
