package org.ood.adporwal.bookstore.services;
import jakarta.transaction.Transactional;
import org.ood.adporwal.bookstore.dto.InventoryItemDTO;
import org.ood.adporwal.bookstore.dto.ResponseDTO;
import org.ood.adporwal.bookstore.entity.Book;
import org.ood.adporwal.bookstore.entity.InventoryItem;
import org.ood.adporwal.bookstore.entity.Transaction;
import org.ood.adporwal.bookstore.enums.Constants;
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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.ood.adporwal.bookstore.enums.Constants.BOOK_DETAIL_LIBRARY;

@Service
public class InventoryItemServiceImpl  implements InventoryItemService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryItemServiceImpl.class);

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @Autowired
    BookDetailsFactory bookDetailsFactory;

    @Qualifier("constantDepreciation")
    @Autowired
    DepreciationStrategy depreciationStrategy;

    @Override
    public List<InventoryItemDTO> getAvailableItems() {
        List<InventoryItem> availableItems = inventoryItemRepository.findByState(InventoryItemState.AVAILABLE);
        logger.info(Constants.LOGGER_BOOK_FOUND + availableItems.size());
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
    public ResponseDTO sellItem(String id) throws InvalidOperationException, ResourceNotFoundException {

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
            Transaction transaction = transactionServiceImpl.recordTransaction(item, TransactionType.SELL);
            logger.debug("Transaction Recorded for Book ID: " + item.getId());

            return new ResponseDTO(item.getId(), Constants.SUCCESS, "Book Sold Successfully", item.getCurrentPrice(),transaction.getId().toString() );

    }
    //Buyback item
    @Transactional
    public ResponseDTO buyback(String id) throws InvalidOperationException, ResourceNotFoundException {

        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID Provided: " + id));

        if (item.getState() != InventoryItemState.SOLD) {
            throw new InvalidOperationException("Item is not available for Buyback.");
        }
        item.setState(item.getState().transition());

        item.depreciatePrice(depreciationStrategy.depreciateValue());

        inventoryItemRepository.save(item);
        Transaction transaction = transactionServiceImpl.recordTransaction(item, TransactionType.BUYBACK);
        return new ResponseDTO(item.getId(), Constants.SUCCESS, "Book Buyback Successful", item.getCurrentPrice(), transaction.getId().toString());
    }

    @Transactional
    public ResponseDTO buyNewItem(String isbn) throws ResourceNotFoundException {
        BookDetails bookDetailsSource = bookDetailsFactory.getBookDetails(BOOK_DETAIL_LIBRARY);
        if(bookDetailsSource == null) {
            throw new ResourceNotFoundException("Book Details resource not found");
        }
        Book book = bookDetailsSource.getDetails(isbn);
        if (book == null) {
            throw new ResourceNotFoundException("Book not found with ISBN: " + isbn);
        }
        String bookId = UUID.randomUUID().toString().substring(0, 11);
        InventoryItem item = new InventoryItem(bookId, book, book.getBasePrice(), 0, InventoryItemState.AVAILABLE);
        inventoryItemRepository.save(item);
        Transaction transaction = transactionServiceImpl.recordTransaction(item, TransactionType.PURCHASE);
        return new ResponseDTO(bookId, Constants.SUCCESS, "Book Purchased Successfully", book.getBasePrice(), transaction.getId().toString());
    }
}
