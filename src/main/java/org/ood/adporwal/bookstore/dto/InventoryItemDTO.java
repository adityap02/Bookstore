package org.ood.adporwal.bookstore.dto;

import org.ood.adporwal.bookstore.entity.Book;
import org.ood.adporwal.bookstore.enums.InventoryItemState;

import java.math.BigDecimal;

public class InventoryItemDTO {
    private String id;
    private String isbn;
    private BigDecimal currentPrice;
    private int transactionCount;

    private String title;
    private String authors;
    private String edition;
    private BigDecimal basePrice;


    // Constructors, Getters, and Setters
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
}
