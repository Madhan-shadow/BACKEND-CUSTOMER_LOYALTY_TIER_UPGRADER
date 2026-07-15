package com.example.Customer_Loyalty_Tier_Upgrader.controller;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.TierUpgradeRule;
import com.example.Customer_Loyalty_Tier_Upgrader.service.TierUpgradeRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tier-rules")
@Tag(name = "Tier Upgrade Rules")
public class TierUpgradeRuleController {

    private final TierUpgradeRuleService tierUpgradeRuleService;

    public TierUpgradeRuleController(TierUpgradeRuleService tierUpgradeRuleService) {
        this.tierUpgradeRuleService = tierUpgradeRuleService;
    }

    @PostMapping
    @Operation(summary = "Create a tier upgrade rule")
    public ResponseEntity<TierUpgradeRule> create(@RequestBody TierUpgradeRule rule) {
        return ResponseEntity.ok(tierUpgradeRuleService.createRule(rule));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a tier upgrade rule")
    public ResponseEntity<TierUpgradeRule> update(@PathVariable Long id,
                                                   @RequestBody TierUpgradeRule rule) {
        return ResponseEntity.ok(tierUpgradeRuleService.updateRule(id, rule));
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active rules")
    public ResponseEntity<List<TierUpgradeRule>> getActive() {
        return ResponseEntity.ok(tierUpgradeRuleService.getActiveRules());
    }

    @GetMapping
    @Operation(summary = "Get all rules")
    public ResponseEntity<List<TierUpgradeRule>> getAll() {
        return ResponseEntity.ok(tierUpgradeRuleService.getAllRules());
    }

    @GetMapping("/lookup")
    @Operation(summary = "Lookup rule by fromTier and toTier")
    public ResponseEntity<TierUpgradeRule> lookup(@RequestParam String fromTier,
                                                   @RequestParam String toTier) {
        return ResponseEntity.ok(tierUpgradeRuleService.getRule(fromTier, toTier));
    }
}
