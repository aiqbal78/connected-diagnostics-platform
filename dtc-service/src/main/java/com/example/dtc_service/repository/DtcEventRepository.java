package com.example.dtc_service.repository;

import com.example.dtc_service.model.DtcEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DtcEventRepository extends JpaRepository<DtcEvent, Long> {
    List<DtcEvent> findByVin(String vin);
}