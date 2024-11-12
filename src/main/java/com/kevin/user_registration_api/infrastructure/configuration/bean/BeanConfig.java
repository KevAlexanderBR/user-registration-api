package com.kevin.user_registration_api.infrastructure.configuration.bean;

import com.kevin.user_registration_api.domain.ports.out.AddressService;
import com.kevin.user_registration_api.domain.ports.out.LogEntryRepository;
import com.kevin.user_registration_api.domain.ports.out.UserRepository;
import com.kevin.user_registration_api.domain.security.JwtTokenProvider;
import com.kevin.user_registration_api.domain.service.AddressServiceImpl;
import com.kevin.user_registration_api.domain.service.LogServiceImpl;
import com.kevin.user_registration_api.domain.service.LoginServiceImpl;
import com.kevin.user_registration_api.domain.service.UserServiceImpl;
import com.kevin.user_registration_api.infrastructure.out.integration.AdressFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserServiceImpl userServiceImpl(UserRepository userRepository, AddressService addressService) {
        return new UserServiceImpl(userRepository, addressService);
    }

    @Bean
    public AddressServiceImpl addressServiceImpl(AdressFeignClient adressFeignClient) {
        return new AddressServiceImpl(adressFeignClient);
    }

    @Bean
    public LogServiceImpl logServiceImpl(LogEntryRepository logEntryRepository) {
        return new LogServiceImpl(logEntryRepository);
    }

    @Bean
    public LoginServiceImpl loginServiceImpl(JwtTokenProvider jwtTokenProvider) {
        return new LoginServiceImpl(jwtTokenProvider);
    }
}
