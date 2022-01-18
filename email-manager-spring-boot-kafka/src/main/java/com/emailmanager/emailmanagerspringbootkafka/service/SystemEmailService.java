package com.emailmanager.emailmanagerspringbootkafka.service;

import com.emailmanager.emailmanagerspringbootkafka.entity.SystemEmail;
import com.emailmanager.emailmanagerspringbootkafka.repository.SystemEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class SystemEmailService {
    // use user repository and resolve dependency using Autowired
    @Autowired
    private SystemEmailRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public SystemEmail saveSystemEmail(SystemEmail email){
        return repository.save(email);
    }

    public List<SystemEmail> saveEmails(List<SystemEmail> emails){
        return repository.saveAll(emails);
    }
}
