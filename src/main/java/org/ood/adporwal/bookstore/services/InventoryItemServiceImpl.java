package org.ood.adporwal.bookstore.services;

import jakarta.transaction.Transactional;
import org.ood.adporwal.bookstore.dto.InventoryItemDTO;
import org.ood.adporwal.bookstore.entity.InventoryItem;
import org.ood.adporwal.bookstore.enums.InventoryItemState;
import org.ood.adporwal.bookstore.exceptions.InvalidOperationException;
import org.ood.adporwal.bookstore.exceptions.ResourceNotFoundException;
import org.ood.adporwal.bookstore.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryItemServiceImpl  implements InventoryItemService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    public void click(){

    }
    @Override
    public List<InventoryItemDTO> getAvailableItems() {
        List<InventoryItem> availableItems = inventoryItemRepository.findByState(InventoryItemState.AVAILABLE);
        return availableItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private InventoryItemDTO convertToDTO(InventoryItem item) {
        return new InventoryItemDTO(
                item.getId(),
                item.getBook().getIsbn(),
                item.getCurrentPrice(),
                item.getTransactionCount(),
                item.getBook()
        );
    }

    @Transactional
    public void buyItem(String id) throws InvalidOperationException, ResourceNotFoundException {

            InventoryItem item = inventoryItemRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found with ID: " + id));

        if (item.getState() != InventoryItemState.AVAILABLE) {
            throw new InvalidOperationException("Item is not available for purchase.");
        }
            item.setState(item.getState().transition());

            // Depreciate price by 10%
            item.depreciatePrice();

            // Save changes
            inventoryItemRepository.save(item);
            //TODO: Implement logic to record transaction in a separate table

    }
    //Buyback item
    @Transactional
    public BigDecimal buyback(String id) {

        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found with ID: " + id));

        if (item.getState() != InventoryItemState.SOLD) {
            throw new InvalidOperationException("Item is not available for Buyback.");
        }
        item.setState(item.getState().transition());

//        // Depreciate price by 10%
//        item.depreciatePrice();

        // Save changes
        inventoryItemRepository.save(item);
        return item.getCurrentPrice();
    }

    public void sellNewItem(String isbn) {
        // Implement logic to sell a new item
    }

    // Additional methods
}
