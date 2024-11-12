package com.kevin.user_registration_api.domain.ports.out;

import com.kevin.user_registration_api.domain.model.LogEntry;

public interface LogEntryRepository {

    void save(LogEntry logEntry);
}
