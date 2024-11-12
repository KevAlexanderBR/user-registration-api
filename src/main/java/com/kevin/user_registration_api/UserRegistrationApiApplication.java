package com.kevin.user_registration_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserRegistrationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRegistrationApiApplication.class, args);
    }

}
