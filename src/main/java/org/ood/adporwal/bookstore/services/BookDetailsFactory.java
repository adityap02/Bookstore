package org.ood.adporwal.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.ood.adporwal.bookstore.enums.Constants.BOOK_DETAIL_API;
import static org.ood.adporwal.bookstore.enums.Constants.BOOK_DETAIL_LIBRARY;
@Service
public class BookDetailsFactory {
    @Autowired
    ExternalAPIBookDetails externalAPI;
    public BookDetails getBookDetails(String source) {
        if (source.equals(BOOK_DETAIL_LIBRARY)) {
            return new BookDetailsFactoryFromLibrary();
        } else if (source.equals(BOOK_DETAIL_API)) {
            return new BookDetailsFactoryAPIAdapter(externalAPI);
        }
        return null;
    }
}
