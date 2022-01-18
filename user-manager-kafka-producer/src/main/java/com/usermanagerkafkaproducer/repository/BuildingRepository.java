package com.usermanagerkafkaproducer.repository;

import com.usermanagerkafkaproducer.entity.Building;
import com.usermanagerkafkaproducer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Integer> {

}
