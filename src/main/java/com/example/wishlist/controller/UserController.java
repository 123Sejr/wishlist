package com.example.wishlist.controller;

import com.example.wishlist.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
}
