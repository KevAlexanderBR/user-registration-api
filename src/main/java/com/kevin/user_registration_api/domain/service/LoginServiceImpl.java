package com.kevin.user_registration_api.domain.service;

import com.kevin.user_registration_api.domain.ports.in.LoginService;
import com.kevin.user_registration_api.domain.security.JwtTokenProvider;

public class LoginServiceImpl implements LoginService {

    private final JwtTokenProvider jwtTokenProvider;

    public LoginServiceImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login() {
        return jwtTokenProvider.generateToken();
    }
}


