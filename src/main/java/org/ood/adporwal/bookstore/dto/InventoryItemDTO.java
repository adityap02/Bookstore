package org.ood.adporwal.bookstore.dto;

import org.ood.adporwal.bookstore.enums.InventoryItemState;

import java.math.BigDecimal;

public class InventoryItemDTO {
    private String id;
    private String isbn;
    private BigDecimal currentPrice;
    private int transactionCount;
    private InventoryItemState state;

    // Constructors, Getters, and Setters
    public InventoryItemDTO() {
    }
    public InventoryItemDTO(String id, String isbn, BigDecimal currentPrice, int transactionCount, InventoryItemState state) {
        this.id = id;
        this.isbn = isbn;
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

    public InventoryItemState getState() {
        return state;
    }

    public void setState(InventoryItemState state) {
        this.state = state;
    }


}
