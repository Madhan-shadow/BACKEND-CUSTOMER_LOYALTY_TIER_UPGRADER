package com.example.Customer_Loyalty_Tier_Upgrader.controller;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.TierHistoryRecord;
import com.example.Customer_Loyalty_Tier_Upgrader.service.TierUpgradeEngineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tier-engine")
@Tag(name = "Tier Upgrade Engine")
public class TierUpgradeEngineController {

    private final TierUpgradeEngineService tierUpgradeEngineService;

    public TierUpgradeEngineController(TierUpgradeEngineService tierUpgradeEngineService) {
        this.tierUpgradeEngineService = tierUpgradeEngineService;
    }

    @PostMapping("/evaluate/{customerId}")
    @Operation(summary = "Evaluate and upgrade customer tier")
    public ResponseEntity<TierHistoryRecord> evaluate(@PathVariable Long customerId) {
        return ResponseEntity.ok(tierUpgradeEngineService.evaluateAndUpgradeTier(customerId));
    }

    @GetMapping("/history/{customerId}")
    @Operation(summary = "Get tier history by customer ID")
    public ResponseEntity<List<TierHistoryRecord>> history(@PathVariable Long customerId) {
        return ResponseEntity.ok(tierUpgradeEngineService.getHistoryByCustomer(customerId));
    }

    @GetMapping
    @Operation(summary = "Get all tier history")
    public ResponseEntity<List<TierHistoryRecord>> getAll() {
        return ResponseEntity.ok(tierUpgradeEngineService.getAllHistory());
    }
}
