package fr.pantheonsorbonne.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dao.EventLogRepository;
import fr.pantheonsorbonne.entity.EventLog;
import fr.pantheonsorbonne.dto.log.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.time.LocalDateTime;

@ApplicationScoped
@Named("eventLogService")
public class EventLogService {

    @Inject
    EventLogRepository repository;

    @Inject
    ObjectMapper objectMapper;


    public void saveEventLog(LogDTO dto) {
        doSave(dto.getType().toString(), dto);
    }
    private void doSave(String eventType, Object dto) {
        try {
            String payload = objectMapper.writeValueAsString(dto);

            EventLog log = new EventLog();
            log.setEventType(eventType);
            log.setTimestamp(LocalDateTime.now());
            log.setPayload(payload);

            repository.persist(log);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while serializing DTO to JSON", e);
        }
    }
}
