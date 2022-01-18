package com.emailmanager.emailmanagerspringbootkafka.repository;

import com.emailmanager.emailmanagerspringbootkafka.entity.SystemEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemEmailRepository extends JpaRepository<SystemEmail, Integer>, SystemEmailRepositoryCustom {

}
