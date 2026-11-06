package com.example.wishlist.service;

import com.example.wishlist.exception.DatabaseOperationException;
import com.example.wishlist.exception.NotFoundException;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.repository.WishListRepository;
import org.springframework.dao.DataAccessException;
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

    public Wishlist findWishListById(int wishlistId) {
        Wishlist wishlist;

        try {
            wishlist = repo.findWishListById(wishlistId);
        } catch (DataAccessException ex) {
            throw new DatabaseOperationException("Ønskelisten kunne ikke hentes.", ex);
        }
        if (wishlist == null) {
            throw new NotFoundException("Ønskelisten med id " + wishlistId + " blev ikke fundet.");
        }

        return wishlist;
    }

    public void deleteWishlist(int wishlistId) {
        repo.deleteWishlist(wishlistId);
    }
}
