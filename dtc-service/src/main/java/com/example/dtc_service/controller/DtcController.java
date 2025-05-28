package com.example.dtc_service.controller;

import com.example.dtc_service.model.DtcEvent;
import com.example.dtc_service.repository.DtcEventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dtc")
public class DtcController {

    private final DtcEventRepository repository;

    public DtcController(DtcEventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{vin}")
    public List<DtcEvent> getDtcByVin(@PathVariable String vin) {
        return repository.findByVin(vin);
    }
}