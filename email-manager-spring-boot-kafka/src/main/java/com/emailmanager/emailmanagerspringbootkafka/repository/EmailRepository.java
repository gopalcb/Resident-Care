package com.emailmanager.emailmanagerspringbootkafka.repository;

import com.emailmanager.emailmanagerspringbootkafka.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Integer>, EmailRepositoryCustom {
    List<Email> findEmailByFolder(@Param("userId") int userId, @Param("folder") String folder);
}
