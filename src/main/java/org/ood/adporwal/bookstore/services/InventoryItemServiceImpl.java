package org.ood.adporwal.bookstore.services;
import jakarta.transaction.Transactional;
import org.ood.adporwal.bookstore.dto.InventoryItemDTO;
import org.ood.adporwal.bookstore.entity.Book;
import org.ood.adporwal.bookstore.entity.InventoryItem;
import org.ood.adporwal.bookstore.enums.InventoryItemState;
import org.ood.adporwal.bookstore.enums.TransactionType;
import org.ood.adporwal.bookstore.exceptions.InvalidOperationException;
import org.ood.adporwal.bookstore.exceptions.ResourceNotFoundException;
import org.ood.adporwal.bookstore.repository.InventoryItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InventoryItemServiceImpl  implements InventoryItemService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryItemServiceImpl.class);

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Qualifier("bookDetailsFactoryFromLibrary")
    @Autowired
    private BookDetailsFactory bookDetailsFactory;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @Override
    public List<InventoryItemDTO> getAvailableItems() {
        List<InventoryItem> availableItems = inventoryItemRepository.findByState(InventoryItemState.AVAILABLE);
        logger.info("Available Books Found: " + availableItems.size());
        return availableItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private InventoryItemDTO convertToDTO(InventoryItem item) {
        logger.info("Converting InventoryItem to InventoryItemDTO");
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
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid Inventory ID: " + id));

        if (item.getState() != InventoryItemState.AVAILABLE) {
            logger.error("Item : " + id + " not available for selling. Inventory State : " + item.getState());
            throw new InvalidOperationException("Item is not available for selling.");
        }
            item.setState(item.getState().transition());

            inventoryItemRepository.save(item);
            logger.info("Book ID : " + item.getId() +" Sold Successfully");
            logger.debug("Book ID : " + item.getId() +" saved Successfully");
            transactionServiceImpl.recordTransaction(item, TransactionType.SELL);
            logger.debug("Transaction Recorded for Book ID: " + item.getId());


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
        Book book = bookDetailsFactory.getDetails(isbn);
        String bookId = UUID.randomUUID().toString().substring(0, 9);
        InventoryItem item = new InventoryItem(bookId, book, book.getBasePrice(), 0, InventoryItemState.AVAILABLE);
        inventoryItemRepository.save(item);
        transactionServiceImpl.recordTransaction(item, TransactionType.PURCHASE);
        return item.getCurrentPrice();
    }


}
