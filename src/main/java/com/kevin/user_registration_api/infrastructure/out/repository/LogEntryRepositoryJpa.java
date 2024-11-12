package com.kevin.user_registration_api.infrastructure.out.repository;

import com.kevin.user_registration_api.infrastructure.entity.LogEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepositoryJpa extends JpaRepository<LogEntryEntity, Long> {
}
