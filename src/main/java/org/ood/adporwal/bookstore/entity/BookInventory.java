package org.ood.adporwal.bookstore.entity;

import jakarta.persistence.*;
import org.ood.adporwal.bookstore.enums.InventoryItemState;

import java.math.BigDecimal;

@Entity
@Table(name = "book_inventory")
public class BookInventory{
    @Id
    private String id; // RFID

    @ManyToOne
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
}

