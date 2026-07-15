package com.example.Customer_Loyalty_Tier_Upgrader.controller;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.CustomerProfile;
import com.example.Customer_Loyalty_Tier_Upgrader.service.CustomerProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Profiles")
public class CustomerProfileController {

    private final CustomerProfileService customerProfileService;

    public CustomerProfileController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    @PostMapping
    @Operation(summary = "Create a new customer profile")
    public ResponseEntity<CustomerProfile> create(@RequestBody CustomerProfile customer) {
        return ResponseEntity.ok(customerProfileService.createCustomer(customer));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID")
    public ResponseEntity<CustomerProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerProfileService.getCustomerById(id));
    }

    @GetMapping
    @Operation(summary = "Get all customers")
    public ResponseEntity<List<CustomerProfile>> getAll() {
        return ResponseEntity.ok(customerProfileService.getAllCustomers());
    }

    @PutMapping("/{id}/tier")
    @Operation(summary = "Update customer tier")
    public ResponseEntity<CustomerProfile> updateTier(@PathVariable Long id,
                                                       @RequestParam String newTier) {
        return ResponseEntity.ok(customerProfileService.updateTier(id, newTier));
    }

    @GetMapping("/lookup/{customerId}")
    @Operation(summary = "Lookup customer by customerId")
    public ResponseEntity<CustomerProfile> lookup(@PathVariable String customerId) {
        return ResponseEntity.ok(customerProfileService.findByCustomerId(customerId));
    }
}
