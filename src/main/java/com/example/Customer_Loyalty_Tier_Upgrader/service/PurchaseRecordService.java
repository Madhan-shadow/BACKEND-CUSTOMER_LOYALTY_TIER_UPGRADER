package com.example.Customer_Loyalty_Tier_Upgrader.service;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.PurchaseRecord;
import java.util.List;

public interface PurchaseRecordService {
    PurchaseRecord recordPurchase(PurchaseRecord purchase);
    List<PurchaseRecord> getPurchasesByCustomer(Long customerId);
    List<PurchaseRecord> getAllPurchases();
    PurchaseRecord getPurchaseById(Long id);
}
