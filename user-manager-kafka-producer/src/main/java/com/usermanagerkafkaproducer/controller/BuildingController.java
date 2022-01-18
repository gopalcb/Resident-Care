package com.usermanagerkafkaproducer.controller;

import com.usermanagerkafkaproducer.entity.Apartment;
import com.usermanagerkafkaproducer.entity.Building;
import com.usermanagerkafkaproducer.entity.Topic;
import com.usermanagerkafkaproducer.entity.User;
import com.usermanagerkafkaproducer.service.BuildingService;
import com.usermanagerkafkaproducer.service.MessageService;
import com.usermanagerkafkaproducer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BuildingController {
    @Autowired
    private BuildingService service;

    /** PostMapping **/
    @PostMapping("/add-building")
    public Building addBuilding(@RequestBody Building building){
        return service.save(building);
    }

    @PostMapping("/add-apt")
    public Apartment addApt(@RequestBody Apartment apt){
        return service.saveApt(apt);
    }

    @PostMapping("/delete-building")
    public boolean deleteBuilding(@RequestBody int id){
        return service.deleteBuilding(id);
    }

    @PostMapping("/delete-apt")
    public boolean deleteApt(@RequestBody int id){
        return service.deleteApt(id);
    }

    /** GetMapping **/
    @GetMapping("/buildings")
    public List<Building> findAllBuildings(){
        return service.getAllBuildings();
    }

    @GetMapping("/apts")
    public List<Apartment> findAllApts(){
        return service.getAllApts();
    }
}
