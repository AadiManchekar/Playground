package com.aadi.inventoryservice.service;

import com.aadi.inventoryservice.entity.Event;
import com.aadi.inventoryservice.repository.EventRepository;
import com.aadi.inventoryservice.repository.VenueRepository;
import com.aadi.inventoryservice.response.EventInventoryResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

  private final EventRepository eventRepository;
  private final VenueRepository venueRepository;

  public InventoryService(EventRepository eventRepository, VenueRepository venueRepository) {
    this.eventRepository = eventRepository;
    this.venueRepository = venueRepository;
  }

  public List<EventInventoryResponse> getAllEvents() {
    List<Event> events = eventRepository.findAll();

    return events.stream()
        .map(
            event ->
                EventInventoryResponse.builder()
                    .event(event.getName())
                    .capacity(event.getLeftCapacity())
                    .venue(event.getVenue())
                    .build())
        .toList();
  }
}
