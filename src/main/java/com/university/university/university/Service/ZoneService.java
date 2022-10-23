package com.university.university.university.Service;

import java.util.List;
import java.util.Optional;

import com.university.university.university.Collections.Zones;

public interface ZoneService {
    public String saveZone(Zones zone);
    public String updateZone(String id,Zones zones);
    public String deleteId(String id);
    public List<Zones> getAll();
    public Optional<Zones> getById(String id);
    public boolean existsById(String id);
    public boolean existsByArea(String area);
}
