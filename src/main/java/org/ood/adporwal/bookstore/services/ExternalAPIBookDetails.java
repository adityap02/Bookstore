package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.entity.Book;
import org.ood.adporwal.bookstore.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ExternalAPIBookDetails {
    public String fetchBookAsJson(String isbn) {
        return "{ \"isbn\": \"" + isbn + "\", \"title\": \"External Book Title\",\"edition\": \"5th Edition\", \"price\": 99.99 }";
    }
}
