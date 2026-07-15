package com.example.Customer_Loyalty_Tier_Upgrader.repository;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.TierHistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TierHistoryRecordRepository extends JpaRepository<TierHistoryRecord, Long> {
    List<TierHistoryRecord> findByCustomerId(Long customerId);
    List<TierHistoryRecord> findByChangedAtBetween(LocalDateTime start, LocalDateTime end);
}
