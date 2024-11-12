package com.kevin.user_registration_api.infrastructure.in.controller.impl;

import com.kevin.user_registration_api.application.dto.UserDTO;
import com.kevin.user_registration_api.domain.model.UserDomain;
import com.kevin.user_registration_api.domain.ports.in.LogService;
import com.kevin.user_registration_api.domain.ports.in.UserService;
import com.kevin.user_registration_api.infrastructure.in.controller.UserControllerSwagger;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements UserControllerSwagger {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @Override
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDomain> users = userService.getAllUsers(pageable);
        Page<UserDTO> usersDTO = users.map(UserDTO::fromDomain);
        return ResponseEntity.ok(usersDTO);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(UserDTO::fromDomain)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = UserDTO.fromDomain(userService.saveUser(userDTO.toDomain()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Override
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO.toDomain())
                .map(UserDTO::fromDomain)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUserById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
