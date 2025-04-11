package com.aadi.inventoryservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aadi.inventoryservice.response.EventInventoryResponse;
import com.aadi.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    
    @GetMapping("/inventory/events")
    public List<EventInventoryResponse> inventoryGetAllEvents() {
        return inventoryService.getAllEvents();
}
