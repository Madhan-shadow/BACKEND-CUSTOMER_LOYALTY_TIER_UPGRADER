package com.example.Customer_Loyalty_Tier_Upgrader.service.impl;

import com.example.Customer_Loyalty_Tier_Upgrader.entity.VisitRecord;
import com.example.Customer_Loyalty_Tier_Upgrader.repository.VisitRecordRepository;
import com.example.Customer_Loyalty_Tier_Upgrader.service.VisitRecordService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    private final VisitRecordRepository visitRecordRepository;
    private static final Set<String> VALID_CHANNELS = Set.of("STORE", "APP", "WEB");

    public VisitRecordServiceImpl(VisitRecordRepository visitRecordRepository) {
        this.visitRecordRepository = visitRecordRepository;
    }

    @Override
    public VisitRecord recordVisit(VisitRecord visit) {
        if (visit.getChannel() == null || !VALID_CHANNELS.contains(visit.getChannel())) {
            throw new IllegalArgumentException("Invalid channel");
        }
        return visitRecordRepository.save(visit);
    }

    @Override
    public List<VisitRecord> getVisitsByCustomer(Long customerId) {
        return visitRecordRepository.findByCustomerId(customerId);
    }

    @Override
    public List<VisitRecord> getAllVisits() {
        return visitRecordRepository.findAll();
    }

    @Override
    public VisitRecord getVisitById(Long id) {
        return visitRecordRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Visit record not found"));
    }
}
