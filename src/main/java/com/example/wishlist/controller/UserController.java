package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import com.example.wishlist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/create-user")
    public String createUser(Model model, User user) {
        model.addAttribute("user", user);
        return "create-user";
    }

    @PostMapping("/create-user")
    public String createUser(User user) {
        service.addUser(user);
        return "redirect:/create-user-success";
    }

    @GetMapping("/create-user-success")
    public String createUserSuccess() {
        return "create-user-success";
    }

    @GetMapping("/")
        public String getAllUsers(Model model) {
            model.addAttribute("users", service.getAllUsers());
            return "index";
        }

}

