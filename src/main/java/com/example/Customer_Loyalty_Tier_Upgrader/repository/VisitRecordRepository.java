package com.example.Customer_Loyalty_Tier_Upgrader.repository;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.VisitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface VisitRecordRepository extends JpaRepository<VisitRecord, Long> {
    List<VisitRecord> findByCustomerId(Long customerId);
    List<VisitRecord> findByVisitDateBetween(LocalDate start, LocalDate end);
}
