package com.kevin.user_registration_api.domain.service;

import com.kevin.user_registration_api.domain.model.LogEntry;
import com.kevin.user_registration_api.domain.ports.in.LogService;
import com.kevin.user_registration_api.domain.ports.out.LogEntryRepository;

public class LogServiceImpl implements LogService {

    private final LogEntryRepository logEntryRepository;

    public LogServiceImpl(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    @Override
    public void log(String endpoint, String method, String parameters, String responseStatus, String response) {
        LogEntry logEntry = new LogEntry();
        logEntry.setEndpoint(endpoint);
        logEntry.setMethod(method);
        logEntry.setParameters(parameters);
        logEntry.setResponseStatus(responseStatus);
        logEntry.setResponse(response);

        logEntryRepository.save(logEntry);
    }
}
