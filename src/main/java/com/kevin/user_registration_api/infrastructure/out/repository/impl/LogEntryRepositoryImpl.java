package com.kevin.user_registration_api.infrastructure.out.repository.impl;

import com.kevin.user_registration_api.application.exception.LogException;
import com.kevin.user_registration_api.domain.model.LogEntry;
import com.kevin.user_registration_api.domain.ports.out.LogEntryRepository;
import com.kevin.user_registration_api.infrastructure.entity.LogEntryEntity;
import com.kevin.user_registration_api.infrastructure.out.repository.LogEntryRepositoryJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogEntryRepositoryImpl implements LogEntryRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogEntryRepositoryImpl.class);

    @Autowired
    private LogEntryRepositoryJpa logEntryRepositoryJpa;

    @Override
    public void save(LogEntry logEntry) {
        try {
            logEntryRepositoryJpa.save(LogEntryEntity.fromDomain(logEntry));
        } catch (Exception e) {
            LOGGER.error("Erro ao gravar log. LOG: {}. Mensagem: {}", logEntry, e.getMessage());
            throw new LogException("Erro ao gravar log. LOG: " + logEntry);
        }

    }
}
