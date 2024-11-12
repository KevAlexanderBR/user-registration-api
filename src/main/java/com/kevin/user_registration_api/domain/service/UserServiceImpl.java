package com.kevin.user_registration_api.domain.service;

import com.kevin.user_registration_api.domain.model.UserDomain;
import com.kevin.user_registration_api.domain.ports.in.UserService;
import com.kevin.user_registration_api.domain.ports.out.AddressService;
import com.kevin.user_registration_api.domain.ports.out.UserRepository;
import com.kevin.user_registration_api.infrastructure.entity.UserEntity;
import com.kevin.user_registration_api.infrastructure.out.integration.AddressResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;


    public UserServiceImpl(UserRepository userRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
    }

    @Override
    public Optional<UserDomain> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDomain saveUser(UserDomain userDomain) {
        AddressResponse addressResponse = addressService.getAddress(userDomain.getAddress().getPostalCode());
        userDomain.setAddress(addressResponse.toAddressDomain(userDomain.getAddress().getId(), userDomain.getAddress().getAdditionalInfo(), userDomain.getAddress().getNumber()));
        UserEntity user = UserEntity.fromDomain(userDomain);
        return userRepository.save(user);
    }

    @Override
    public Page<UserDomain> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<UserDomain> updateUser(UserDomain userDomain) {
        AddressResponse addressResponse = addressService.getAddress(userDomain.getAddress().getPostalCode());
        userDomain.setAddress(addressResponse.toAddressDomain(userDomain.getAddress().getId(), userDomain.getAddress().getAdditionalInfo(), userDomain.getAddress().getNumber()));
        return userRepository.findById(userDomain.getId()).map(updatedUser -> {
            updatedUser.setName(userDomain.getName());
            updatedUser.setEmail(userDomain.getEmail());
            updatedUser.setAddress(userDomain.getAddress());
            return userRepository.save(UserEntity.fromDomain(updatedUser));
        });
    }

    @Override
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
