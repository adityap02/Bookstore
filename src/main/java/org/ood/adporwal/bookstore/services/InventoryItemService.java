package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.dto.InventoryItemDTO;
import org.ood.adporwal.bookstore.exceptions.InvalidOperationException;

import java.math.BigDecimal;
import java.util.List;

public interface InventoryItemService {
    public List<InventoryItemDTO> getAvailableItems();
    public void buyItem(String id) throws InvalidOperationException;
    public BigDecimal buyback(String id);
    public void sellNewItem(String isbn);
}
