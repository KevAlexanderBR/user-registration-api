package com.kevin.user_registration_api.infrastructure.out.repository.impl;

import com.kevin.user_registration_api.application.exception.LogException;
import com.kevin.user_registration_api.domain.model.LogEntry;
import com.kevin.user_registration_api.infrastructure.entity.LogEntryEntity;
import com.kevin.user_registration_api.infrastructure.out.repository.LogEntryRepositoryJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LogEntryRepositoryImplTest {

    @Mock
    private LogEntryRepositoryJpa postgressLogEntryRepository;

    @InjectMocks
    private LogEntryRepositoryImpl logEntryRepositoryImpl;

    private static final Logger LOGGER = LoggerFactory.getLogger(LogEntryRepositoryImpl.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldSaveLogEntry_WhenNoExceptionOccurs() {
        LogEntry logEntry = new LogEntry();
        logEntry.setId(1L);
        logEntry.setTimestamp(LocalDateTime.now());

        logEntryRepositoryImpl.save(logEntry);

        verify(postgressLogEntryRepository, times(1)).save(any(LogEntryEntity.class));
    }

    @Test
    void save_ShouldThrowLogException_WhenExceptionOccurs() {
        LogEntry logEntry = new LogEntry();

        doThrow(new RuntimeException("Erro ao salvar no banco")).when(postgressLogEntryRepository).save(any(LogEntryEntity.class));

        assertThrows(LogException.class, () -> logEntryRepositoryImpl.save(logEntry));

        verify(postgressLogEntryRepository, times(1)).save(any(LogEntryEntity.class));
    }
}
