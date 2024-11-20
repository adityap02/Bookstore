package org.ood.adporwal.bookstore.services;
import org.json.JSONObject;
import org.ood.adporwal.bookstore.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Component
public class BookDetailsFactoryAPIAdapter implements BookDetails {

    @Autowired
    ExternalAPIBookDetails externalAPI;

    public BookDetailsFactoryAPIAdapter(ExternalAPIBookDetails externalAPI) {
        this.externalAPI = externalAPI;
    }

    @Override
    public Book getDetails(String isbn) {
        String jsonResponse = externalAPI.fetchBookAsJson(isbn);

        JSONObject jsonObject = new JSONObject(jsonResponse);
        String fetchedIsbn = jsonObject.getString("isbn");
        String title = jsonObject.getString("title");
        String edition = jsonObject.getString("edition");
        BigDecimal price = jsonObject.getBigDecimal("price");
        // Map the JSON data to a Book object
        return Book.builder().isbn(fetchedIsbn).title(title).edition(edition).basePrice(price).build();
    }
}
