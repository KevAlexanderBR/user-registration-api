package com.kevin.user_registration_api.infrastructure.configuration.bean;

import com.kevin.user_registration_api.domain.ports.out.AddressService;
import com.kevin.user_registration_api.domain.ports.out.LogEntryRepository;
import com.kevin.user_registration_api.domain.ports.out.UserRepository;
import com.kevin.user_registration_api.domain.security.JwtTokenProvider;
import com.kevin.user_registration_api.infrastructure.out.integration.AdressFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BeanConfigTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AdressFeignClient adressFeignClient;

    @Autowired
    private LogEntryRepository logEntryRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private BeanConfig beanConfig;

    @BeforeEach
    void setUp() {
        beanConfig = new BeanConfig();
    }

    @Test
    void userServiceImplTest() {
        assertNotNull(beanConfig.userServiceImpl(userRepository, addressService));
    }

    @Test
    void addressServiceImplTest() {
        assertNotNull(beanConfig.addressServiceImpl(adressFeignClient));
    }

    @Test
    void logServiceImplTest() {
        assertNotNull(beanConfig.logServiceImpl(logEntryRepository));
    }

    @Test
    void loginServiceImplTest() {
        assertNotNull(beanConfig.loginServiceImpl(jwtTokenProvider));
    }
}
