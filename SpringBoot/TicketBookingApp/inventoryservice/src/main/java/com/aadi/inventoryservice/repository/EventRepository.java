package com.aadi.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository <Event, Long> {
    
}
