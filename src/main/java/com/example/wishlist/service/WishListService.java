package com.example.wishlist.service;

import com.example.wishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    private final WishListRepository repo;

    public WishListService(WishListRepository repo) {
        this.repo = repo;
    }
}
