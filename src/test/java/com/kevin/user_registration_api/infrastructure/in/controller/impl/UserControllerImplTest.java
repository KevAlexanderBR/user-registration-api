package com.kevin.user_registration_api.infrastructure.in.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.user_registration_api.application.dto.AddressDTO;
import com.kevin.user_registration_api.application.dto.UserDTO;
import com.kevin.user_registration_api.domain.model.AddressDomain;
import com.kevin.user_registration_api.domain.model.UserDomain;
import com.kevin.user_registration_api.domain.ports.in.LogService;
import com.kevin.user_registration_api.domain.ports.in.UserService;
import com.kevin.user_registration_api.infrastructure.configuration.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserControllerImpl.class)
@Import(SecurityConfig.class)
@WithMockUser
class UserControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private LogService logService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsersEmptyTest() throws Exception {
        when(userService.getAllUsers(any())).thenReturn(Page.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsersTest() throws Exception {
        AddressDomain addressDomain = new AddressDomain(1L, "00000000", "Rua 1", "casa 1", "bairro 1", "1", "SP", "Sao Paulo");
        when(userService.getAllUsers(any())).thenReturn(new PageImpl<>(List.of(new UserDomain(1L, "Test User", "testuser@example.com", addressDomain))));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getUserByIdTest() throws Exception {
        Long userId = 1L;
        AddressDomain addressDomain = new AddressDomain(1L, "00000000", "Rua 1", "casa 1", "bairro 1", "1", "SP", "Sao Paulo");

        UserDomain userDomain = new UserDomain(userId, "Test User", "testuser@example.com", addressDomain);
        when(userService.getUserById(any())).thenReturn(Optional.of(userDomain));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test User"));
    }

    @Test
    void getUserByIdNotFoundTest() throws Exception {
        Long userId = 1L;
        when(userService.getUserById(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUserTest() throws Exception {
        AddressDTO addressDTO = new AddressDTO(1L, "00000000", "Rua 1", "casa 1", "bairro 1", "1", "SP", "Sao Paulo");
        UserDTO userDTO = new UserDTO(1L, "New User", "newuser@example.com", addressDTO);
        UserDomain userDomain = userDTO.toDomain();
        when(userService.saveUser(any())).thenReturn(userDomain);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New User"));
    }

    @Test
    void updateUserTest() throws Exception {
        AddressDTO addressDTO = new AddressDTO(1L, "00000000", "Rua 1", "casa 1", "bairro 1", "1", "SP", "Sao Paulo");

        UserDTO userDTO = new UserDTO(1L, "Updated User", "updateduser@example.com", addressDTO);
        UserDomain userDomain = userDTO.toDomain();
        when(userService.updateUser(any())).thenReturn(Optional.of(userDomain));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated User"));
    }

    @Test
    void updateUserNotFoundTest() throws Exception {
        AddressDTO addressDTO = new AddressDTO(1L, "00000000", "Rua 1", "casa 1", "bairro 1", "1", "SP", "Sao Paulo");

        UserDTO userDTO = new UserDTO(1L, "Updated User", "updateduser@example.com", addressDTO);
        when(userService.updateUser(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserNotFoundTest() throws Exception {
        Long userId = 1L;
        when(userService.deleteUserById(userId)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", userId))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserNoContentTest() throws Exception {
        Long userId = 1L;
        when(userService.deleteUserById(userId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", userId))
                .andExpect(status().isNoContent());
    }
}
