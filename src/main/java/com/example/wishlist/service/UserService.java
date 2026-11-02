package com.example.wishlist.service;

import com.example.wishlist.exception.DatabaseOperationException;
import com.example.wishlist.exception.DuplicateIdException;
import com.example.wishlist.model.User;
import com.example.wishlist.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void addUser(User user) {
        try {
            repo.addUser(user);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateIdException("Id eksisterer allerede.", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Bruger kunne ikke oprettes.", e);

        }

    }
}
