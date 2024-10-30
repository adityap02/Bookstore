package org.ood.adporwal.bookstore.entity;

import jakarta.persistence.*;
import org.ood.adporwal.bookstore.enums.InventoryItemState;

import java.math.BigDecimal;

@Entity
@Table(name = "book_inventory")
public class InventoryItem {
    @Id
    private String id; // RFID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_isbn")
    private Book book;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @Column(name = "transaction_count")
    private int transactionCount;

    @Enumerated(EnumType.STRING)
    private InventoryItemState state;

    // Constructors, Getters, and Setters

    public void depreciatePrice() {
        this.currentPrice = this.currentPrice.multiply(BigDecimal.valueOf(0.9));
        this.transactionCount++;
    }

    public InventoryItem() {
    }
    public InventoryItem(String id, Book book, BigDecimal currentPrice, int transactionCount, InventoryItemState state) {
        this.id = id;
        this.book = book;
        this.currentPrice = currentPrice;
        this.transactionCount = transactionCount;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public InventoryItemState getState() {
        return state;
    }

    public void setState(InventoryItemState state) {
        this.state = state;
    }
}

