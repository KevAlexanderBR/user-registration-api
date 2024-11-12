package com.kevin.user_registration_api.infrastructure.out.repository.impl;

import com.kevin.user_registration_api.application.exception.UserException;
import com.kevin.user_registration_api.domain.model.UserDomain;
import com.kevin.user_registration_api.domain.ports.out.UserRepository;
import com.kevin.user_registration_api.infrastructure.entity.UserEntity;
import com.kevin.user_registration_api.infrastructure.out.repository.UserRepositoryJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Override
    public Optional<UserDomain> findById(Long id) {
        try {
            return userRepositoryJpa.findById(id).map(UserEntity::toDomain);
        } catch (Exception e) {
            LOGGER.error("Erro ao buscar usuario. ID: {}. Mensagem:{}.", id, e.getMessage());
            throw new UserException("Erro ao buscar usuario. ID: " + id);
        }

    }

    @Override
    @Transactional
    public UserDomain save(UserEntity user) {
        try {
            return userRepositoryJpa.save(user).toDomain();
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar usuário. Nome: {}. Mensagem:{}.", user.getName(), e.getMessage());
            throw new UserException("Erro ao salvar usuário. Nome: " + user.getName());
        }
    }

    @Override
    public Page<UserDomain> findAll(Pageable pageable) {
        try {
            return userRepositoryJpa.findAll(pageable).map(UserEntity::toDomain);
        } catch (Exception e) {
            LOGGER.error("Erro ao buscar lista de usuarios. Mensagem:{}.", e.getMessage());
            throw new UserException("Erro ao buscar lista de usuarios");
        }
    }

    @Override
    public Boolean existsById(Long id) {
        try {
            return userRepositoryJpa.existsById(id);
        } catch (Exception e) {
            LOGGER.error("Erro ao verificar se o usuario existe. ID: {} Mensagem:{}.", id, e.getMessage());
            throw new UserException("Erro ao verificar se o usuario existe. ID: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            userRepositoryJpa.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar usuario. ID: {} Mensagem:{}.", id, e.getMessage());
            throw new UserException("Erro ao deletar usuario. ID: " + id);
        }
    }
}
