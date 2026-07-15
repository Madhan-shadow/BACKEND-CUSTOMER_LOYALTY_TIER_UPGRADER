package com.example.Customer_Loyalty_Tier_Upgrader.controller;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.VisitRecord;
import com.example.Customer_Loyalty_Tier_Upgrader.service.VisitRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
@Tag(name = "Visit Records")
public class VisitRecordController {

    private final VisitRecordService visitRecordService;

    public VisitRecordController(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }

    @PostMapping
    @Operation(summary = "Record a visit")
    public ResponseEntity<VisitRecord> record(@RequestBody VisitRecord visit) {
        return ResponseEntity.ok(visitRecordService.recordVisit(visit));
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get visits by customer ID")
    public ResponseEntity<List<VisitRecord>> byCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(visitRecordService.getVisitsByCustomer(customerId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get visit by ID")
    public ResponseEntity<VisitRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(visitRecordService.getVisitById(id));
    }

    @GetMapping
    @Operation(summary = "Get all visits")
    public ResponseEntity<List<VisitRecord>> getAll() {
        return ResponseEntity.ok(visitRecordService.getAllVisits());
    }
}
