package com.emailmanager.emailmanagerspringbootkafka.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SystemEmailRepositoryImpl implements SystemEmailRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

}
