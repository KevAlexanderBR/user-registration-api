package com.kevin.user_registration_api.infrastructure.out.repository;

import com.kevin.user_registration_api.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {
}