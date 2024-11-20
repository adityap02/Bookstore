package org.ood.adporwal.bookstore.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ood.adporwal.bookstore.enums.Constants;
import org.ood.adporwal.bookstore.enums.InventoryItemState;

import java.math.BigDecimal;

@Entity
@Table(name = "book_inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    public void depreciatePrice(BigDecimal depreciationRate) {
        this.currentPrice = this.currentPrice.multiply(BigDecimal.valueOf(1).subtract(depreciationRate));
        this.transactionCount++;
    }
}

