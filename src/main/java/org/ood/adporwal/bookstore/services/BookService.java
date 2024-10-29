package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.dto.BookDTO;
import org.ood.adporwal.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        return null;
    }

    // Additional methods
}
