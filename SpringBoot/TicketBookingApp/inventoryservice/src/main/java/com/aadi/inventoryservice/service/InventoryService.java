package com.aadi.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aadi.inventoryservice.response.EventInventoryResponse;

@Service
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public InventoryService(EventRepository eventRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    public List<EventInventoryResponse> getAllEvents() {
        return null;    
    }
    
}
