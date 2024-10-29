package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.dto.InventoryItemDTO;

import java.util.List;

public interface InventoryItemService {
    public List<InventoryItemDTO> getAvailableItems();
    public void buyItem(String id);
    public void sellItem(String id);
    public void sellNewItem(String isbn);
}
