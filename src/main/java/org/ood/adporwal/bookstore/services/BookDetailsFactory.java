package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.entity.Book;

public interface BookDetailsFactory {
    Book getDetails(String isbn);
}
