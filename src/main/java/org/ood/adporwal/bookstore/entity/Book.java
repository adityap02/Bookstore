package org.ood.adporwal.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "books")
public class Book {
    @Id
    private String isbn;

    private String title;
    private String authors;
    private String edition;

    @Column(name = "base_price")
    private BigDecimal basePrice;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<InventoryItem> inventoryItems;

}
