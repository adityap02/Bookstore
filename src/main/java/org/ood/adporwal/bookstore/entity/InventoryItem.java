package org.ood.adporwal.bookstore.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ood.adporwal.bookstore.enums.Constants;
import org.ood.adporwal.bookstore.enums.InventoryItemState;

import java.math.BigDecimal;

@Entity
@Table(name = "book_inventory")
@Getter
@Setter
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


    public void depreciatePrice() {
        this.currentPrice = this.currentPrice.multiply(BigDecimal.valueOf(1).subtract(Constants.DEPRECIATION_RATE));
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
}

