package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.entity.Book;
import org.ood.adporwal.bookstore.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookDetailsFactoryFromAPI implements BookDetailsFactory {
    @Override
    public Book getDetails(String isbn) throws ResourceNotFoundException {
        //TODO: Implement this method - Future Scope
        return null;
    }
}
