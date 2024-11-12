package com.kevin.user_registration_api.infrastructure.out.repository.impl;

import com.kevin.user_registration_api.application.exception.UserException;
import com.kevin.user_registration_api.domain.model.UserDomain;
import com.kevin.user_registration_api.infrastructure.entity.AddressEntity;
import com.kevin.user_registration_api.infrastructure.entity.UserEntity;
import com.kevin.user_registration_api.infrastructure.out.repository.UserRepositoryJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryImplTest {

    @Mock
    private UserRepositoryJpa userRepositoryJpa;

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdTest() {
        Long userId = 1L;
        AddressEntity addressEntity = new AddressEntity(1L, "", "","","","","","");
        UserEntity userEntity = new UserEntity();
        userEntity.setName("John Doe");
        userEntity.setEmail("johndoe@email.com");
        userEntity.setAddress(addressEntity);
        userEntity.setId(userId);
        when(userRepositoryJpa.findById(userId)).thenReturn(Optional.of(userEntity));

        Optional<UserDomain> result = userRepositoryImpl.findById(userId);

        assertTrue(result.isPresent());
        verify(userRepositoryJpa, times(1)).findById(userId);
    }

    @Test
    void findByIdExceptionTest() {
        Long userId = 1L;
        when(userRepositoryJpa.findById(userId)).thenThrow(new RuntimeException("Erro ao buscar usuario"));

        assertThrows(UserException.class, () -> userRepositoryImpl.findById(userId));
        verify(userRepositoryJpa, times(1)).findById(userId);
    }

    @Test
    void saveTest() {
        AddressEntity addressEntity = new AddressEntity(1L, "", "","","","","","");
        UserEntity userEntity = new UserEntity();
        userEntity.setName("John Doe");
        userEntity.setEmail("johndoe@email.com");
        userEntity.setAddress(addressEntity);
        when(userRepositoryJpa.save(userEntity)).thenReturn(userEntity);

        UserDomain result = userRepositoryImpl.save(userEntity);

        assertNotNull(result);
        verify(userRepositoryJpa, times(1)).save(userEntity);
    }

    @Test
    void saveExceptionTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("John Doe");
        when(userRepositoryJpa.save(userEntity)).thenThrow(new RuntimeException("Erro ao salvar usuário"));

        assertThrows(UserException.class, () -> userRepositoryImpl.save(userEntity));
        verify(userRepositoryJpa, times(1)).save(userEntity);
    }

    @Test
    void findAllTest() {
        AddressEntity addressEntity = new AddressEntity(1L, "", "","","","","","");
        UserEntity userEntity = new UserEntity();
        userEntity.setName("John Doe");
        userEntity.setEmail("johndoe@email.com");
        userEntity.setAddress(addressEntity);
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserEntity> page = new PageImpl<>(List.of(userEntity));
        when(userRepositoryJpa.findAll(pageable)).thenReturn(page);

        Page<UserDomain> result = userRepositoryImpl.findAll(pageable);

        assertNotNull(result);
        verify(userRepositoryJpa, times(1)).findAll(pageable);
    }

    @Test
    void findAllExceptionTest() {
        Pageable pageable = PageRequest.of(0, 10);
        when(userRepositoryJpa.findAll(pageable)).thenThrow(new RuntimeException("Erro ao buscar lista de usuários"));

        assertThrows(UserException.class, () -> userRepositoryImpl.findAll(pageable));
        verify(userRepositoryJpa, times(1)).findAll(pageable);
    }

    @Test
    void existsByIdTest() {
        Long userId = 1L;
        when(userRepositoryJpa.existsById(userId)).thenReturn(true);

        Boolean result = userRepositoryImpl.existsById(userId);

        assertTrue(result);
        verify(userRepositoryJpa, times(1)).existsById(userId);
    }

    @Test
    void existsByIdExceptionTest() {
        Long userId = 1L;
        when(userRepositoryJpa.existsById(any())).thenThrow(new RuntimeException("Erro ao verificar usuário"));

        assertThrows(UserException.class, () -> userRepositoryImpl.existsById(userId));
        verify(userRepositoryJpa, times(1)).existsById(userId);
    }

    @Test
    void deleteByIdTest() {
        Long userId = 1L;

        userRepositoryImpl.deleteById(userId);

        verify(userRepositoryJpa, times(1)).deleteById(userId);
    }

    @Test
    void deleteByIdExceptionTest() {
        Long userId = 1L;
        doThrow(new RuntimeException("Erro ao deletar usuario")).when(userRepositoryJpa).deleteById(userId);

        assertThrows(UserException.class, () -> userRepositoryImpl.deleteById(userId));
        verify(userRepositoryJpa, times(1)).deleteById(userId);
    }
}
