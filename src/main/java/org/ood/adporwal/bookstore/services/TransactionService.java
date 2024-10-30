package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.entity.InventoryItem;
import org.ood.adporwal.bookstore.enums.TransactionType;

public interface TransactionService {
    public void recordTransaction(InventoryItem item, TransactionType transactionType);
}
