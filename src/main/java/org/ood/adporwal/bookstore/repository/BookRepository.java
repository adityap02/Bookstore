package org.ood.adporwal.bookstore.repository;

import org.ood.adporwal.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
    Book findByIsbn(String isbn);
}
