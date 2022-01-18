package com.usermanagerkafkaproducer.repository;

import com.usermanagerkafkaproducer.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> getUserByEmail(String email);
    List<User> getUsersByPropertyType(int propertyId, String key);
    List<User> getTenants();
}
