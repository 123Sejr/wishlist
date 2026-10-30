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


    //GET-endpoint, der opretter en ny ønskeliste.
    //Returnerer html-siden "createWishlistForm".
    @GetMapping("/{userId}/createWishlist")
    public String createWishlist(@PathVariable int userId, Model model) {
        model.addAttribute("userId", userId);
        return "createWishlistForm";
    }


    //POST-endpointet, der gemmer den nye ønskeliste.
    //Returnere brugeren tilbage til siden showWishlists, hvor den nye ønskeliste nu kan ses.
    @PostMapping("/saveWishlist")
    public String saveNewList(@ModelAttribute Wishlist wishlist, Model model) {
        service.createWishlist(wishlist);
        model.addAttribute("wishlist", wishlist);
        return "redirect:/showWishlists";
    }
}
