package com.example.Customer_Loyalty_Tier_Upgrader.service;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.TierHistoryRecord;
import java.util.List;

public interface TierUpgradeEngineService {
    TierHistoryRecord evaluateAndUpgradeTier(Long customerId);
    List<TierHistoryRecord> getHistoryByCustomer(Long customerId);
    List<TierHistoryRecord> getAllHistory();
}
