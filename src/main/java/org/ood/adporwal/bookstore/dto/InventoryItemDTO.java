package org.ood.adporwal.bookstore.dto;

import lombok.Getter;
import lombok.Setter;
import org.ood.adporwal.bookstore.entity.Book;
import org.ood.adporwal.bookstore.enums.InventoryItemState;

import java.math.BigDecimal;

@Getter
@Setter
public class InventoryItemDTO {
    private String id;
    private String isbn;
    private BigDecimal currentPrice;
    private int transactionCount;

    private String title;
    private String authors;
    private String edition;
    private BigDecimal basePrice;


    public InventoryItemDTO() {
    }
    public InventoryItemDTO(String id, String isbn, BigDecimal currentPrice, int transactionCount, Book book) {
        this.id = id;
        this.isbn = isbn;
        this.currentPrice = currentPrice;
        this.transactionCount = transactionCount;
        this.title = book.getTitle();
        this.authors = book.getAuthors();
        this.edition = book.getEdition();
        this.basePrice = book.getBasePrice();
    }

}
