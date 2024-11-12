package com.kevin.user_registration_api.infrastructure.entity;

import com.kevin.user_registration_api.domain.model.LogEntry;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "log_entry")
public class LogEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    private String endpoint;

    private String method;

    @Column(columnDefinition = "TEXT")
    private String parameters;

    private String responseStatus;

    @Column(columnDefinition = "TEXT")
    private String response;

    public static LogEntryEntity fromDomain(LogEntry logEntry) {
        return new LogEntryEntity(logEntry.getId(), logEntry.getTimestamp(), logEntry.getEndpoint(), logEntry.getMethod(),
                logEntry.getParameters(), logEntry.getResponseStatus(), logEntry.getResponse());
    }

}
