package com.example.wishlist.service;

import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishService {
    private final WishRepository repo;

    public WishService(WishRepository repo) {
        this.repo = repo;
    }

    // Denne annotation ruller alle ændringer tilbage, hvis en eneste del af metoden fejler
    // https://dev.to/gianfcop98/transactions-in-spring-boot-what-transactional-really-does-and-why-it-matters-56a6
    @Transactional
    public void createWish(Wish wish){
        repo.createWish(wish);
    }
}
