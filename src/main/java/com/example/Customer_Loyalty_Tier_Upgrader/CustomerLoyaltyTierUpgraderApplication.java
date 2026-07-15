package com.example.Customer_Loyalty_Tier_Upgrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CustomerLoyaltyTierUpgraderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerLoyaltyTierUpgraderApplication.class, args);
	}
}
