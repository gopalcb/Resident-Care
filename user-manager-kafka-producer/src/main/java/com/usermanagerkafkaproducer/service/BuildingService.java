package com.usermanagerkafkaproducer.service;

import com.usermanagerkafkaproducer.entity.Apartment;
import com.usermanagerkafkaproducer.entity.Building;
import com.usermanagerkafkaproducer.entity.User;
import com.usermanagerkafkaproducer.repository.AptRepository;
import com.usermanagerkafkaproducer.repository.BuildingRepository;
import com.usermanagerkafkaproducer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository repository;

    @Autowired
    private AptRepository aptRepository;

    public Building save(Building building){
        return repository.save(building);
    }

    public Apartment saveApt(Apartment apt){
        return aptRepository.save(apt);
    }

    public List<Building> getAllBuildings(){
        return repository.findAll();
    }

    public List<Apartment> getAllApts(){
        return aptRepository.findAll();
    }

    public boolean deleteBuilding(int id){
        repository.deleteById(id);
        return true;
    }

    public boolean deleteApt(int id){
        aptRepository.deleteById(id);
        return true;
    }
}
