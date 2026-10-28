package com.example.wishlist.controller;

import com.example.wishlist.service.WishListService;
import org.springframework.stereotype.Controller;

@Controller
public class WishListController {
    private final WishListService servive;

    public WishListController(WishListService servive) {
        this.servive = servive;
    }
}
