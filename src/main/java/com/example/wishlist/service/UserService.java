package com.example.wishlist.service;

import com.example.wishlist.exception.DatabaseOperationException;
import com.example.wishlist.exception.DuplicateIdException;
import com.example.wishlist.exception.NotFoundException;
import com.example.wishlist.model.User;
import com.example.wishlist.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new DuplicateIdException("Id'et" + user.getId() + "eksisterer allerede.", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Bruger kunne ikke oprettes.", e);
        }
    }

    public List<User> getAllUsers() {
        return  repo.getAllUsers();
    }


    public User findUserById(int id) {
        User user;

        try {
            user = repo.findUserbyId(id);
        } catch (DataAccessException ex) {
            throw new DatabaseOperationException("Brugeren kunne ikke hentes.", ex);
        }
        if (user == null) {
            throw new NotFoundException("Brugeren med id " + id + " blev ikke fundet.");
        }

        return user;
    }


    public void deleteUser(int id) {
        repo.deleteUser(id);
    }

    public void editUser(User user) {
        repo.editUser(user);}
}
