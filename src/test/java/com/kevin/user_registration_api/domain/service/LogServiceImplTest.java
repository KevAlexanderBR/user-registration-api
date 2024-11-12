package com.kevin.user_registration_api.domain.service;

import com.kevin.user_registration_api.domain.model.LogEntry;
import com.kevin.user_registration_api.domain.ports.out.LogEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LogServiceImplTest {

    @Mock
    private LogEntryRepository logEntryRepository;

    @InjectMocks
    private LogServiceImpl logService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void logTest() {
        String endpoint = "/api/test";
        String method = "POST";
        String parameters = "{ \"param\": \"value\" }";
        String responseStatus = "200 OK";
        String response = "{ \"message\": \"Success\" }";

        logService.log(endpoint, method, parameters, responseStatus, response);

        verify(logEntryRepository, times(1)).save(any(LogEntry.class));
    }
}
