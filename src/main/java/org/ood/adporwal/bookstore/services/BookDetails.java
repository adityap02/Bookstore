package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.entity.Book;

public interface BookDetails {
    Book getDetails(String isbn);
}
