package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.dto.BookDTO;
import org.ood.adporwal.bookstore.entity.Book;
import org.ood.adporwal.bookstore.exceptions.ResourceNotFoundException;
import org.ood.adporwal.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookByIsbn(String id) throws ResourceNotFoundException {
            return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + id));

    }


}
