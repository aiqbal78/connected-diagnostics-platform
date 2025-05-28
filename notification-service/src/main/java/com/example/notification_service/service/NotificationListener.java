package com.example.notification_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    @KafkaListener(topics = "vehicle-health-alerts", groupId = "notification-group")
    public void listen(String message){
        System.out.println("Notification Received: " + message);
    }
}
