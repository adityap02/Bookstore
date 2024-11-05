package org.ood.adporwal.bookstore.services;
import org.ood.adporwal.bookstore.entity.Book;
import org.ood.adporwal.bookstore.exceptions.ResourceNotFoundException;

import java.util.List;

public interface BookService {
    Book getBookByIsbn(String id) throws ResourceNotFoundException;
}
