package com.emailmanager.emailmanagerspringbootkafka.repository;

import com.emailmanager.emailmanagerspringbootkafka.entity.Email;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmailRepositoryImpl implements EmailRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Email> findEmailByFolder(int userId, String folder) {
        /*Query query = entityManager.createNativeQuery("select * from emails e" +
                "where e.to_user_id = ? and e.folder = ? ", Email.class);

        query.setParameter(1, userId);
        query.setParameter(2, folder);

        return query.getResultList();*/
        return null;
    }
}
