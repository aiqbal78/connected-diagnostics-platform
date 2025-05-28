package com.example.dtc_service.kafka;

import com.example.dtc_service.model.DtcEvent;
import com.example.dtc_service.repository.DtcEventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DtcEventListener {

    private final ObjectMapper objectMapper;
    private final DtcEventRepository repository;

    public DtcEventListener(ObjectMapper objectMapper, DtcEventRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    @KafkaListener(topics = "vehicle.dtc.events", groupId = "dtc-service-group")
    public void listen(String message) {
        try {
            DtcEvent event = objectMapper.readValue(message, DtcEvent.class);
            repository.save(event);
            System.out.println("Saved DTC: " + event);
        } catch (Exception e) {
            System.err.println("Failed to process message: " + message);
            e.printStackTrace();
        }
    }

}