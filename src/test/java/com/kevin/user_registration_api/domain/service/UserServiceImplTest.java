package com.kevin.user_registration_api.domain.service;

import com.kevin.user_registration_api.domain.model.AddressDomain;
import com.kevin.user_registration_api.domain.model.UserDomain;
import com.kevin.user_registration_api.domain.ports.out.AddressService;
import com.kevin.user_registration_api.domain.ports.out.UserRepository;
import com.kevin.user_registration_api.infrastructure.entity.UserEntity;
import com.kevin.user_registration_api.infrastructure.out.integration.AddressResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserByIdTest() {
        Long userId = 1L;
        UserDomain userDomain = createUserDomain(userId, "User Name", "user@example.com", createAddressDomain());
        when(userRepository.findById(userId)).thenReturn(Optional.of(userDomain));

        Optional<UserDomain> result = userService.getUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
    }

    @Test
    void saveUserTest() {
        UserDomain userDomain = createUserDomain(1L, "Kevin", "kevin@example.com", createAddressDomain());
        AddressResponse addressResponse = createAddressResponse();

        when(addressService.getAddress(userDomain.getAddress().getPostalCode())).thenReturn(addressResponse);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userDomain);

        UserDomain savedUser = userService.saveUser(userDomain);

        verify(addressService).getAddress(userDomain.getAddress().getPostalCode());
        verify(userRepository).save(any(UserEntity.class));

        assertEquals(userDomain.getName(), savedUser.getName());
        assertEquals(userDomain.getEmail(), savedUser.getEmail());
    }

    @Test
    void getAllUsersTest() {
        PageRequest pageable = PageRequest.of(0, 10);
        UserDomain userDomain = createUserDomain(1L, "Kevin", "kevin@example.com", createAddressDomain());
        Page<UserDomain> userPage = new PageImpl<>(Collections.singletonList(userDomain));

        when(userRepository.findAll(pageable)).thenReturn(userPage);

        Page<UserDomain> result = userService.getAllUsers(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(userDomain.getId(), result.getContent().get(0).getId());
    }

    @Test
    void updateUserTest() {
        Long userId = 1L;
        AddressDomain addressDomain = createAddressDomain();
        UserDomain userDomain = createUserDomain(userId, "New Name", "newemail@example.com", addressDomain);
        UserDomain existingUser = createUserDomain(userId, "Old Name", "oldemail@example.com", addressDomain);
        AddressResponse addressResponse = createAddressResponse();

        when(addressService.getAddress(userDomain.getAddress().getPostalCode())).thenReturn(addressResponse);
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userDomain);

        Optional<UserDomain> updatedUser = userService.updateUser(userDomain);

        assertTrue(updatedUser.isPresent());
        assertEquals(userDomain.getName(), updatedUser.get().getName());
        assertEquals(userDomain.getEmail(), updatedUser.get().getEmail());
    }

    @Test
    void deleteUserByIdTest() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);

        boolean result = userService.deleteUserById(userId);

        assertTrue(result);
        verify(userRepository).deleteById(userId);
    }

    @Test
    void deleteUserByIdNotFoundTest() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(false);

        boolean result = userService.deleteUserById(userId);

        assertFalse(result);
        verify(userRepository, never()).deleteById(userId);
    }

    private UserDomain createUserDomain(Long id, String name, String email, AddressDomain address) {
        UserDomain userDomain = new UserDomain();
        userDomain.setId(id);
        userDomain.setName(name);
        userDomain.setEmail(email);
        userDomain.setAddress(address);
        return userDomain;
    }

    private AddressDomain createAddressDomain() {
        AddressDomain addressDomain = new AddressDomain();
        addressDomain.setId(1L);
        addressDomain.setPostalCode("99999999");
        addressDomain.setNumber("25c");
        addressDomain.setState("SP");
        addressDomain.setStreet("Avenida X");
        addressDomain.setStateName("São Paulo");
        addressDomain.setNeighborhood("Bairro X");
        addressDomain.setAdditionalInfo("Perto do hospital X");
        return addressDomain;
    }

    private AddressResponse createAddressResponse() {
        return new AddressResponse("99999999", "Avenida X", "", "", "Bairro X", "", "SP", "São Paulo");
    }
}