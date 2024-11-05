package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.entity.Book;
import org.ood.adporwal.bookstore.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDetailsFactoryFromLibrary implements BookDetailsFactory {

    @Autowired
    private BookServiceImpl bookServiceImpl;
    @Override
    public Book getDetails(String isbn) throws ResourceNotFoundException {
        return bookServiceImpl.getBookByIsbn(isbn);
    }
}

