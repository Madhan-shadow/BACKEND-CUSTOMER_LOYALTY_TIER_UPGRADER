package com.example.Customer_Loyalty_Tier_Upgrader.security;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.CustomerProfile;
import com.example.Customer_Loyalty_Tier_Upgrader.repository.CustomerProfileRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerProfileRepository customerProfileRepository;

    public CustomUserDetailsService(CustomerProfileRepository customerProfileRepository) {
        this.customerProfileRepository = customerProfileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerProfile customer = customerProfileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String role = customer.getRole() != null ? customer.getRole() : "ROLE_USER";
        if (!role.startsWith("ROLE_")) role = "ROLE_" + role;
        return new User(customer.getEmail(), customer.getPassword(),
                List.of(new SimpleGrantedAuthority(role)));
    }
}
