package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.dto.InventoryItemDTO;
import org.ood.adporwal.bookstore.entity.BookInventory;
import org.ood.adporwal.bookstore.enums.InventoryItemState;
import org.ood.adporwal.bookstore.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryItemServiceImpl  implements InventoryItemService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    public List<InventoryItemDTO> getAvailableItems() {
        List<BookInventory> availableItems = inventoryItemRepository.findByState(InventoryItemState.AVAILABLE);
        return availableItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private InventoryItemDTO convertToDTO(BookInventory item) {
        return new InventoryItemDTO(
                item.getId(),
                item.getBook().getIsbn(),
                item.getCurrentPrice(),
                item.getTransactionCount(),
                item.getState()
        );
    }
    public void buyItem(String id) {
        
    }

    public void sellItem(String id) {
        // Implement sell logic
    }

    public void sellNewItem(String isbn) {
        // Implement logic to sell a new item
    }

    // Additional methods
}
