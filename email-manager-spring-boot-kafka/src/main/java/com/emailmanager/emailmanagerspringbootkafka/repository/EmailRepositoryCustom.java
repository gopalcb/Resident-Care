package com.emailmanager.emailmanagerspringbootkafka.repository;

import com.emailmanager.emailmanagerspringbootkafka.entity.Email;

import java.util.List;

public interface EmailRepositoryCustom {
    List<Email> findEmailByFolder(int userId, String folder);
}
