package com.example.vehicle_health_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class VehicleHealthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleHealthServiceApplication.class, args);
	}

}
