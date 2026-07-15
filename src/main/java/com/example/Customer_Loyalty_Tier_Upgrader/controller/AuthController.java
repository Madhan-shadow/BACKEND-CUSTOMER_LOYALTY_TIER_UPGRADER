package com.example.Customer_Loyalty_Tier_Upgrader.controller;

import com.example.Customer_Loyalty_Tier_Upgrader.dto.*;
import com.example.Customer_Loyalty_Tier_Upgrader.entity.CustomerProfile;
import com.example.Customer_Loyalty_Tier_Upgrader.security.JwtUtil;
import com.example.Customer_Loyalty_Tier_Upgrader.service.CustomerProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final CustomerProfileService customerProfileService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(CustomerProfileService customerProfileService,
                          JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.customerProfileService = customerProfileService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new customer")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        CustomerProfile customer = new CustomerProfile();
        customer.setCustomerId(request.getEmail());
        customer.setEmail(request.getEmail());
        customer.setFullName(request.getFullName());
        customer.setPhone(request.getPhone());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setRole(request.getRole() != null ? request.getRole() : "USER");
        customer.setCurrentTier("BRONZE");
        CustomerProfile saved = customerProfileService.createCustomer(customer);
        return ResponseEntity.ok(new ApiResponse(true, "Registered successfully", saved));
    }

    @PostMapping("/login")
    @Operation(summary = "Login and get JWT token")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        try {
            CustomerProfile customer = customerProfileService.findByCustomerId(request.getEmail());
            if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
                return ResponseEntity.status(401)
                        .body(new ApiResponse(false, "Invalid credentials"));
            }
            String token = jwtUtil.generateToken(customer.getId(), customer.getEmail(), customer.getRole());
            return ResponseEntity.ok(new ApiResponse(true, "Login successful",
                    Map.of("token", token, "customer", customer)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid credentials"));
        }
    }
}
