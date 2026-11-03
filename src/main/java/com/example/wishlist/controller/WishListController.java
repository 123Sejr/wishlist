package com.example.wishlist.controller;

import com.example.wishlist.model.Wishlist;
import com.example.wishlist.service.UserService;
import com.example.wishlist.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WishListController {
    private final WishListService service;

    public WishListController(WishListService service) {
        this.service = service;
    }

    //GET-endpoint, der viser alle ønskelisterne for en bestemt bruger.
    //Returnerer html-siden "showWishlists".
    @GetMapping("/show-wishlists")
    public String showAllLists(@RequestParam int userId, Model model) {
        List<Wishlist> wishlists = service.showAllWishlistByUserId(userId);
        model.addAttribute("wishlists", wishlists);
        model.addAttribute("userId", userId);
        return "show-wishlists";
    }


    //GET-endpoint, der opretter en ny ønskeliste.
    //Returnerer html-siden "createWishlistForm".
    @GetMapping("/{userId}/createWishlist")
    public String createWishlist(@PathVariable int userId, Model model) {
        //@RequestParam(value = "back-button") int userId
        model.addAttribute("userId", userId);
        return "create-wishlist-form";
    }


    //POST-endpointet, der gemmer den nye ønskeliste.
    //Returnere brugeren tilbage til siden showWishlists, hvor den nye ønskeliste nu kan ses.
    @PostMapping("/{userId}/saveWishlist")
    public String saveNewList(@PathVariable int userId, @ModelAttribute Wishlist wishlist, Model model) {
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("userId", userId);
        service.createWishlist(wishlist, userId);
        return "save-wishlist";
    }
}
