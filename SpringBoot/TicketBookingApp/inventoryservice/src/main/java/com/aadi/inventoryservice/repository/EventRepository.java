package com.aadi.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aadi.inventoryservice.entity.Event;

public interface EventRepository extends JpaRepository <Event, Long> {
    
}
