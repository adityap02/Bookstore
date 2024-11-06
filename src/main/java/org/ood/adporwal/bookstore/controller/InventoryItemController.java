package org.ood.adporwal.bookstore.controller;
import org.ood.adporwal.bookstore.dto.InventoryItemDTO;
import org.ood.adporwal.bookstore.exceptions.InvalidOperationException;
import org.ood.adporwal.bookstore.exceptions.ResourceNotFoundException;
import org.ood.adporwal.bookstore.services.InventoryItemService;
import org.ood.adporwal.bookstore.utils.GenerateErrorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<?> getAvailableInventoryItems() {
        try{
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(inventoryItemService.getAvailableItems());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenerateErrorResponseDTO.generateErrorResponseDTO(ex.getMessage()));
        }
    }

    // POST /api/inventory/buy/{id}
    @PostMapping("/sell/{id}")
    public ResponseEntity<?> sellItem(@PathVariable String id) {
        try {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(inventoryItemService.sellItem(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenerateErrorResponseDTO.generateErrorResponseDTO(ex.getMessage()));
        }
        catch (InvalidOperationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenerateErrorResponseDTO.generateErrorResponseDTO(ex.getMessage()));
        }
    }

    @PostMapping("/buyback/{id}")
    public ResponseEntity<?> buyback(@PathVariable String id) {
        try {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(inventoryItemService.buyback(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenerateErrorResponseDTO.generateErrorResponseDTO(ex.getMessage()));
        }
        catch (InvalidOperationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenerateErrorResponseDTO.generateErrorResponseDTO(ex.getMessage()));
        }
    }

    @PostMapping("/buy/{isbn}")
    public ResponseEntity<?> buy(@PathVariable String isbn) {
        try {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(inventoryItemService.buyNewItem(isbn));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(GenerateErrorResponseDTO.generateErrorResponseDTO(ex.getMessage()));
        }
    }

}
