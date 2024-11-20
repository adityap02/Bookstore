package org.ood.adporwal.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/")
    public String ping() {
        return "Hello & Welcome to Bookstore!";
    }
}
