package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.dto.InventoryItemDTO;
import org.ood.adporwal.bookstore.dto.ResponseDTO;
import org.ood.adporwal.bookstore.exceptions.InvalidOperationException;

import java.util.List;

public interface InventoryItemService {
    public List<InventoryItemDTO> getAvailableItems();
    public ResponseDTO sellItem(String id) throws InvalidOperationException;
    public ResponseDTO buyback(String id);
    public ResponseDTO buyNewItem(String isbn);
}
