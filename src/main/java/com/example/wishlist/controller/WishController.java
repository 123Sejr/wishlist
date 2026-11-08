package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WishController {
    private final WishService service;

    public WishController(WishService service) {
        this.service = service;
    }


    // Finder et wish ud fra dets id og viser det.
    @GetMapping("/{wishlistId}/show-wish/{wishId}")
    public String showWish(Model model, @PathVariable int wishlistId, @PathVariable int wishId){
        Wish wish = service.getWishById(wishId);
        model.addAttribute(wish);
        return "show-wish";
    }

    @GetMapping("{wishlistId}/create-wish")
    public String createWish(Model model, @PathVariable int wishlistId){
        model.addAttribute("wishlistId", wishlistId);
        String wishlistName = service.findWishListNameById(wishlistId);
        model.addAttribute("wishlistName", wishlistName);
        return "create-wish";
    }

    @PostMapping("/{wishlistId}/save-wish")
    public String saveWish(@PathVariable int wishlistId, @ModelAttribute Wish wish, Model model){
        model.addAttribute("wish", wish);
        model.addAttribute("wishlistId", wishlistId);
        service.createWish(wish, wishlistId);
        return "save-wish";
    }

    //GET-endpoint, der åbner en viser ønskerne på en specifik ønskeliste.
    //Returnerer html-siden "open-wishlist".
    @GetMapping("/{wishlistId}/open-wishlist")
    public String openWishlist(@PathVariable int wishlistId, Model model) {
        List<Wish> wishes = service.getAllWishesByListId(wishlistId);
        Wishlist wishlist = service.getListFromWishlistId(wishlistId);
        model.addAttribute("wishes", wishes);
        model.addAttribute("wishlistId", wishlistId);
        model.addAttribute("wishlistName", wishlist.getName());
        model.addAttribute("userId", service.getUserIdFromWishlist(wishlistId));
        return "open-wishlist";
    }

    //POST-endpoint der sletter et ønske fra en liste.
    //Returnere siden delete-wish.html
    @PostMapping("/{wishId}/delete-wish")
    public String deleteWish(@PathVariable int wishId, Model model) {
        Wish wish = service.getWishById(wishId);
        model.addAttribute("name", wish.getName());
        model.addAttribute("wishlistId", wish.getListId());
        service.deleteWish(wishId);
        return "delete-wish";
    }

    @GetMapping("/{wishlistId}/edit-wish/{wishId}")
    public String editWish(@PathVariable int wishlistId, @PathVariable int wishId, Model model) {
        Wish wish = service.getWishById(wishId);
        model.addAttribute(wish);
        model.addAttribute(wishlistId);
        String wishlistName = service.findWishListNameById(wishlistId);
        model.addAttribute("wishlistName", wishlistName);
        return "edit-wish";
    }

    @PostMapping("/update-wish/{wishId}")
    public String updateWish(@ModelAttribute("wish") Wish wish, Model model) {
        service.editWish(wish);
        model.addAttribute("wish", wish);
        return "update-wish";
    }

}
