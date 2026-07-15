package com.example.Customer_Loyalty_Tier_Upgrader.service;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.VisitRecord;
import java.util.List;

public interface VisitRecordService {
    VisitRecord recordVisit(VisitRecord visit);
    List<VisitRecord> getVisitsByCustomer(Long customerId);
    List<VisitRecord> getAllVisits();
    VisitRecord getVisitById(Long id);
}
