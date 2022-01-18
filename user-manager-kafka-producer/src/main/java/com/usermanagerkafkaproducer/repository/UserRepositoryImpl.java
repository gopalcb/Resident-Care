package com.usermanagerkafkaproducer.repository;

import com.usermanagerkafkaproducer.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getUserByEmail(String email) {
        Query query = entityManager.createNativeQuery("select * from users u" +
                "where u.email = ? ", User.class);

        query.setParameter(1, email);

        return query.getResultList();
    }

    @Override
    public List<User> getUsersByPropertyType(int propertyId, String key) {
        Query query = entityManager.createNativeQuery("select * from users u" +
                "where u." + key + " = ? ", User.class);

        query.setParameter(1, propertyId);

        return query.getResultList();
    }

    @Override
    public List<User> getTenants() {
        Query query = entityManager.createNativeQuery("select * from users u" +
                "where u.role = ?", User.class);

        query.setParameter(1, "tenant");

        return query.getResultList();
    }
}
