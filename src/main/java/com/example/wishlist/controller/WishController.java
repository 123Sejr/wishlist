package com.example.wishlist.controller;

import com.example.wishlist.service.WishService;
import org.springframework.stereotype.Controller;

@Controller
public class WishController {
    private final WishService service;

    public WishController(WishService service) {
        this.service = service;
    }
}
