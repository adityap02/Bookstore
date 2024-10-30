package org.ood.adporwal.bookstore.services;

import jakarta.transaction.Transactional;
import org.ood.adporwal.bookstore.dto.InventoryItemDTO;
import org.ood.adporwal.bookstore.entity.Book;
import org.ood.adporwal.bookstore.entity.InventoryItem;
import org.ood.adporwal.bookstore.enums.InventoryItemState;
import org.ood.adporwal.bookstore.enums.TransactionType;
import org.ood.adporwal.bookstore.exceptions.InvalidOperationException;
import org.ood.adporwal.bookstore.exceptions.ResourceNotFoundException;
import org.ood.adporwal.bookstore.repository.BookRepository;
import org.ood.adporwal.bookstore.repository.InventoryItemRepository;
import org.ood.adporwal.bookstore.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InventoryItemServiceImpl  implements InventoryItemService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;


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
    public void sellItem(String id) throws InvalidOperationException, ResourceNotFoundException {

            InventoryItem item = inventoryItemRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found with ID: " + id));

        if (item.getState() != InventoryItemState.AVAILABLE) {
            throw new InvalidOperationException("Item is not available for selling.");
        }
            item.setState(item.getState().transition());

            // Save changes
            inventoryItemRepository.save(item);
            transactionServiceImpl.recordTransaction(item, TransactionType.SELL);


    }
    //Buyback item
    @Transactional
    public BigDecimal buyback(String id) throws InvalidOperationException, ResourceNotFoundException {

        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID Provided: " + id));

        if (item.getState() != InventoryItemState.SOLD) {
            throw new InvalidOperationException("Item is not available for Buyback.");
        }
        item.setState(item.getState().transition());

        // Depreciate price by 10%
        item.depreciatePrice();

        // Save changes
        inventoryItemRepository.save(item);
        transactionServiceImpl.recordTransaction(item, TransactionType.BUYBACK);
        return item.getCurrentPrice();
    }

    @Transactional
    public BigDecimal buyNewItem(String isbn) throws ResourceNotFoundException {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
        String bookId = UUID.randomUUID().toString();
        InventoryItem item = new InventoryItem(bookId, book, book.getBasePrice(), 0, InventoryItemState.AVAILABLE);
        inventoryItemRepository.save(item);
        transactionServiceImpl.recordTransaction(item, TransactionType.PURCHASE);
        return item.getCurrentPrice();
    }
}
