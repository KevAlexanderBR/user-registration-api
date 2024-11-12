package com.kevin.user_registration_api.domain.ports.in;

import com.kevin.user_registration_api.domain.model.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Optional<UserDomain> getUserById(Long id);

    UserDomain saveUser(UserDomain userDomain);

    Page<UserDomain> getAllUsers(Pageable pageable);

    Optional<UserDomain> updateUser(UserDomain userDomain);

    boolean deleteUserById(Long id);
}
