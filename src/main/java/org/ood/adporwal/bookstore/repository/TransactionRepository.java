package org.ood.adporwal.bookstore.repository;

import org.ood.adporwal.bookstore.entity.InventoryItem;
import org.ood.adporwal.bookstore.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByInventoryItem(InventoryItem inventoryItem);
}

