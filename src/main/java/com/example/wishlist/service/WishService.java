package com.example.wishlist.service;

import com.example.wishlist.exception.DatabaseOperationException;
import com.example.wishlist.exception.DuplicateIdException;
import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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

        try {
            repo.createWish(wish);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateIdException("Id eksisterer allerede.", e);
        } catch (DataAccessException e){
            throw new DatabaseOperationException("Profil kunne ikke oprettes.", e);

        }
    }

    public void validateLink(){};

    public void valiateEmail(){};
}
