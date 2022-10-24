package com.university.university.university.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.university.university.Collections.Zones;
import com.university.university.university.Repository.ZoneRepository;
import com.university.university.university.Service.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    ZoneRepository zoneRepository;

    @Override
    public String saveZone(Zones zone) {
        zoneRepository.save(zone);
        return "Zone Saved Successfully";
    }

    @Override
    public String updateZone(String id, Zones newZones) {
        Zones zoness=zoneRepository.findById(id).get();
        zoness.setArea(newZones.getArea());
        zoness.setZone(newZones.getZone());
        zoneRepository.save(zoness);
        return "Zone Updated Successfully";
    }

    @Override
    public String deleteId(String id) {
        zoneRepository.deleteById(id);
        return "Zone Deleted Successfully";
    }

    @Override
    public List<Zones> getAll() {
        return zoneRepository.findAll();
    }

    @Override
    public boolean existsById(String id) {
        if (!(zoneRepository.existsById(id))) {
			return false;
		}
		return true;
    }

    @Override
    public boolean existsByArea(String area) {
        if (!(zoneRepository.existsByArea(area))) {
			return false;
		}
		return true;
    }

    @Override
    public Optional<Zones> getById(String id) {
        return zoneRepository.findById(id);
    }
   

}
