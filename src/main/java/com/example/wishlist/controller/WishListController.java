package com.example.wishlist.controller;

import com.example.wishlist.model.Wishlist;
import com.example.wishlist.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WishListController {
    private final WishListService service;

    public WishListController(WishListService service) {
        this.service = service;
    }

    //GET-endpoint, der viser alle ønskelisterne for en bestemt bruger.
    //Returnerer html-siden "showWishlists".
    @GetMapping("/{userId}")
    public String showAllLists(@PathVariable int userId, Model model) {
        List<Wishlist> wishlists = service.showAllWishlistByUserId(userId);
        model.addAttribute("wishlists", wishlists);
        return "showWishlists";
    }



    @GetMapping("/createWishlist")
    public String createWishlist(Model model) {
        return "createWishlistForm";
    }


    @PostMapping
    public String saveNewList(@ModelAttribute Wishlist wishlist, Model model) {
        service.createWishlist(wishlist);
        return "redirect:/showWishlists";
    }
}
