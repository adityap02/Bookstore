package org.ood.adporwal.bookstore.entity;

import jakarta.persistence.*;
import org.ood.adporwal.bookstore.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_item_id")
    private BookInventory inventoryItem;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    private BigDecimal price;

    // Constructors, Getters, and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookInventory getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(BookInventory inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}