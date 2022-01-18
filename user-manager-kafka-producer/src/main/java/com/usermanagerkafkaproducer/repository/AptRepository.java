package com.usermanagerkafkaproducer.repository;

import com.usermanagerkafkaproducer.entity.Apartment;
import com.usermanagerkafkaproducer.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AptRepository extends JpaRepository<Apartment, Integer> {

}
