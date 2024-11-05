package org.ood.adporwal.bookstore.services;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GetTimestamp {
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

}