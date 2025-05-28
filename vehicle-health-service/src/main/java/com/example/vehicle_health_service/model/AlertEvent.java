package com.example.vehicle_health_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.time.LocalDateTime;

public class AlertEvent {
    private String vin;
    private String alertMessage;


    private Instant timestamp;

    // Constructors, getters, setters

    public AlertEvent(String vin, String alertMessage, Instant timestamp) {
        this.vin = vin;
        this.alertMessage = alertMessage;
        this.timestamp = timestamp;
    }
}