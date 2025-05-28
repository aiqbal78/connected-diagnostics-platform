package com.example.vehicle_health_service.service;

import com.example.vehicle_health_service.model.AlertEvent;
import com.example.vehicle_health_service.model.SensorEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SensorEventListener {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final double TEMP_THRESHOLD = 80.0;
    public SensorEventListener(KafkaTemplate<String,String> kafkaTemplate,ObjectMapper objectMapper){
        this.kafkaTemplate= kafkaTemplate;
        this.objectMapper = objectMapper;
    }
    @KafkaListener(topics = "vehicle.sensor.events", groupId = "vehicle-health-group")
    public void listen(String message){
        try {
            SensorEvent sensorEvent =   objectMapper.readValue(message, SensorEvent.class);
            if("temperature".equalsIgnoreCase(sensorEvent.getSensorType()) && sensorEvent.getValue() > TEMP_THRESHOLD){
                AlertEvent alertEvent = new AlertEvent(sensorEvent.getVin(),"High temperature detected:",sensorEvent.getTimestamp());
                String alertJson = objectMapper.writeValueAsString(alertEvent);
                kafkaTemplate.send("vehicle.alerts", alertJson);
                System.out.println("Sent alert: " + alertJson);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
