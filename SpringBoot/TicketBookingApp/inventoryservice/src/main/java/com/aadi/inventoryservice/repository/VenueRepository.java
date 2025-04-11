package com.aadi.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aadi.inventoryservice.entity.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    
}
