package com.nymble.nymble.service;

import com.nymble.nymble.model.TravelPackage;
import com.nymble.nymble.repositories.TravelPackageRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;

    public TravelPackageService(TravelPackageRepository travelPackageRepository) {
        this.travelPackageRepository = travelPackageRepository;
    }

    public Optional<TravelPackage> getTravelPackageWithDetails(Long travelPackageId) {
        return travelPackageRepository.findById(travelPackageId);
    }

    public TravelPackage getTravelPackageWithPassengers(Long travelPackageId) {
        return (TravelPackage) travelPackageRepository.findByIdWithPassengers(travelPackageId);
    }
}
