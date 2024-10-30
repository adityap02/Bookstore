package org.ood.adporwal.bookstore.controller;

import org.ood.adporwal.bookstore.dto.InventoryItemDTO;
import org.ood.adporwal.bookstore.exceptions.InvalidOperationException;
import org.ood.adporwal.bookstore.exceptions.ResourceNotFoundException;
import org.ood.adporwal.bookstore.services.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
        try {
            inventoryItemService.buyItem(id);
            return ResponseEntity.ok("Purchase successful");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch (InvalidOperationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/buyback/{id}")
    public ResponseEntity<?> buyback(@PathVariable String id) {
        try {
            BigDecimal price = inventoryItemService.buyback(id);
            return ResponseEntity.ok("Buyback successful at price: " + price);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch (InvalidOperationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }



    // Additional endpoints
}
