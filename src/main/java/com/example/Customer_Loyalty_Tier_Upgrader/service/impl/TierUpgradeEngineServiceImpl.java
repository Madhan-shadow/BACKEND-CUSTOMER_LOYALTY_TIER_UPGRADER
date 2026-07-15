package com.example.Customer_Loyalty_Tier_Upgrader.service.impl;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.*;
import com.example.Customer_Loyalty_Tier_Upgrader.repository.*;
import com.example.Customer_Loyalty_Tier_Upgrader.service.TierUpgradeEngineService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {

    private final CustomerProfileRepository customerProfileRepository;
    private final PurchaseRecordRepository purchaseRecordRepository;
    private final VisitRecordRepository visitRecordRepository;
    private final TierUpgradeRuleRepository tierUpgradeRuleRepository;
    private final TierHistoryRecordRepository tierHistoryRecordRepository;

    public TierUpgradeEngineServiceImpl(CustomerProfileRepository customerProfileRepository,
                                        PurchaseRecordRepository purchaseRecordRepository,
                                        VisitRecordRepository visitRecordRepository,
                                        TierUpgradeRuleRepository tierUpgradeRuleRepository,
                                        TierHistoryRecordRepository tierHistoryRecordRepository) {
        this.customerProfileRepository = customerProfileRepository;
        this.purchaseRecordRepository = purchaseRecordRepository;
        this.visitRecordRepository = visitRecordRepository;
        this.tierUpgradeRuleRepository = tierUpgradeRuleRepository;
        this.tierHistoryRecordRepository = tierHistoryRecordRepository;
    }

    @Override
    public TierHistoryRecord evaluateAndUpgradeTier(Long customerId) {
        CustomerProfile customer = customerProfileRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        double totalSpend = purchaseRecordRepository.findByCustomerId(customerId)
                .stream().mapToDouble(PurchaseRecord::getAmount).sum();
        long totalVisits = visitRecordRepository.findByCustomerId(customerId).size();

        String currentTier = customer.getCurrentTier();
        List<TierUpgradeRule> activeRules = tierUpgradeRuleRepository.findByActiveTrue();

        for (TierUpgradeRule rule : activeRules) {
            if (rule.getFromTier().equals(currentTier)
                    && totalSpend >= rule.getMinSpend()
                    && totalVisits >= rule.getMinVisits()) {
                String oldTier = currentTier;
                customer.setCurrentTier(rule.getToTier());
                customerProfileRepository.save(customer);

                TierHistoryRecord history = new TierHistoryRecord(
                        customerId, oldTier, rule.getToTier(),
                        "Upgraded from " + oldTier + " to " + rule.getToTier(),
                        LocalDateTime.now());
                return tierHistoryRecordRepository.save(history);
            }
        }
        return null;
    }

    @Override
    public List<TierHistoryRecord> getHistoryByCustomer(Long customerId) {
        return tierHistoryRecordRepository.findByCustomerId(customerId);
    }

    @Override
    public List<TierHistoryRecord> getAllHistory() {
        return tierHistoryRecordRepository.findAll();
    }
}
