package com.kevin.user_registration_api.domain.ports.out;

import com.kevin.user_registration_api.domain.model.UserDomain;
import com.kevin.user_registration_api.infrastructure.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {

    Optional<UserDomain> findById(Long id);

    UserDomain save(UserEntity user);

    Page<UserDomain> findAll(Pageable pageable);

    Boolean existsById(Long id);

    void deleteById(Long id);
}
