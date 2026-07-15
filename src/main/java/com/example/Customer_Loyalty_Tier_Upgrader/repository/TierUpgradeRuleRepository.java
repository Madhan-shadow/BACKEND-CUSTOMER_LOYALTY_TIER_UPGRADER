package com.example.Customer_Loyalty_Tier_Upgrader.repository;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.TierUpgradeRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TierUpgradeRuleRepository extends JpaRepository<TierUpgradeRule, Long> {
    Optional<TierUpgradeRule> findByFromTierAndToTier(String fromTier, String toTier);
    List<TierUpgradeRule> findByActiveTrue();
}
