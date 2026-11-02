package com.example.wishlist.service;

import com.example.wishlist.model.Wishlist;
import com.example.wishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    private final WishListRepository repo;

    public WishListService(WishListRepository repo) {
        this.repo = repo;
    }


    public List<Wishlist> showAllWishlistByUserId(int id) {
        return repo.showAllWishlistByUserId(id);
    }

    public void createWishlist(Wishlist wishlist, int user_id) {
        repo.createWishlist(wishlist, user_id);
    }
}
