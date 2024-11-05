package org.ood.adporwal.bookstore.dto;

import lombok.Getter;
import lombok.Setter;
import org.ood.adporwal.bookstore.entity.Book;

import java.math.BigDecimal;

@Getter
@Setter
public class BookDTO {
    private String isbn;
    private String title;
    private String authors;
    private String edition;
    private BigDecimal basePrice;

    // Constructors, Getters, and Setters

    public BookDTO(String isbn, String title, String authors, String edition, BigDecimal basePrice) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.edition = edition;
        this.basePrice = basePrice;
    }

    public BookDTO(Book book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.authors = book.getAuthors();
        this.edition = book.getEdition();
        this.basePrice = book.getBasePrice();
    }
}
