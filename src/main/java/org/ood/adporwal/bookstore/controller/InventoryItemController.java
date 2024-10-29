package org.ood.adporwal.bookstore.controller;

import org.ood.adporwal.bookstore.dto.InventoryItemDTO;
import org.ood.adporwal.bookstore.services.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryItemController {

    @Autowired
    private InventoryItemService inventoryItemService;

    // GET /api/inventory
    @GetMapping
    public List<InventoryItemDTO> getAvailableInventoryItems() {

        return inventoryItemService.getAvailableItems();
    }

    // POST /api/inventory/buy/{id}
    @PostMapping("/buy/{id}")
    public ResponseEntity<?> buyItem(@PathVariable String id) {
        inventoryItemService.buyItem(id);
        return ResponseEntity.ok("Purchase successful");
    }

    // Additional endpoints
}
