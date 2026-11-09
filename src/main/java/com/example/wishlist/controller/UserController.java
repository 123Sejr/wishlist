package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import com.example.wishlist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    //POST-endpoint, der sletter en user, samt dens lister og listens ønsker.
    //Returnere siden delete-user.html
    @PostMapping("/{id}/delete-user")
    public String deleteUser(@PathVariable int id, Model model) {
        User user = service.findUserById(id);
        model.addAttribute("name", user.getName());
        service.deleteUser(id);
        return "delete-user";
    }

    @GetMapping("/{id}/edit-user")
    public String editUser(@PathVariable int id, Model model) {
        User user = service.findUserById(id);
        model.addAttribute("user", user);
        return "edit-user";
    }


    @PostMapping("/update-user")
    public String updateUser(Model model, @ModelAttribute("user") User user) {
        service.editUser(user);
        model.addAttribute("user", user);
        return "update-user";
    }



}

