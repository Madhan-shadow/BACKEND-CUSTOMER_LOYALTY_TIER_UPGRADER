package com.example.Customer_Loyalty_Tier_Upgrader.controller;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.PurchaseRecord;
import com.example.Customer_Loyalty_Tier_Upgrader.service.PurchaseRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@Tag(name = "Purchase Records")
public class PurchaseRecordController {

    private final PurchaseRecordService purchaseRecordService;

    public PurchaseRecordController(PurchaseRecordService purchaseRecordService) {
        this.purchaseRecordService = purchaseRecordService;
    }

    @PostMapping
    @Operation(summary = "Record a purchase")
    public ResponseEntity<PurchaseRecord> record(@RequestBody PurchaseRecord purchase) {
        return ResponseEntity.ok(purchaseRecordService.recordPurchase(purchase));
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get purchases by customer ID")
    public ResponseEntity<List<PurchaseRecord>> byCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(purchaseRecordService.getPurchasesByCustomer(customerId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get purchase by ID")
    public ResponseEntity<PurchaseRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseRecordService.getPurchaseById(id));
    }

    @GetMapping
    @Operation(summary = "Get all purchases")
    public ResponseEntity<List<PurchaseRecord>> getAll() {
        return ResponseEntity.ok(purchaseRecordService.getAllPurchases());
    }
}
