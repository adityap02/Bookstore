package org.ood.adporwal.bookstore.repository;

import org.ood.adporwal.bookstore.entity.Book;
import org.ood.adporwal.bookstore.entity.InventoryItem;
import org.ood.adporwal.bookstore.enums.InventoryItemState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, String> {
    List<InventoryItem> findByState(InventoryItemState state);
    List<InventoryItem> findByBook(Book book);
}

