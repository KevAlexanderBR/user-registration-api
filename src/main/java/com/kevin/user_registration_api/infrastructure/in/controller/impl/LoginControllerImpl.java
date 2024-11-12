package com.kevin.user_registration_api.infrastructure.in.controller.impl;

import com.kevin.user_registration_api.application.dto.LoginDTO;
import com.kevin.user_registration_api.domain.ports.in.LoginService;
import com.kevin.user_registration_api.infrastructure.in.controller.LoginSwagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginControllerImpl implements LoginSwagger {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<LoginDTO> login() {
        LoginDTO loginDTO = new LoginDTO(loginService.login());
        return ResponseEntity.status(HttpStatus.CREATED).body(loginDTO);
    }
}
