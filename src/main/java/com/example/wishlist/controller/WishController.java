package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WishController {
    private final WishService service;

    public WishController(WishService service) {
        this.service = service;
    }

    @GetMapping("{wishlistId}/create-wish")
    public String createWish(Model model, @PathVariable int wishlistId){
        model.addAttribute("wishlistID", wishlistId);
        return "create-wish";
    }

    @PostMapping("/save-wish")
    public String saveWish(Wish wish, Model model){
        model.addAttribute("wish", wish);
        service.createWish(wish);
        return "save-wish";
    }

    //GET-endpoint, der åbner en viser ønskerne på en specifik ønskeliste.
    //Returnerer html-siden "open-wishlist".
    @GetMapping("/{wishlistId}/open-wishlist")
    public String openWishlist(@PathVariable int wishlistId, Model model) {
        List<Wish> wishes = service.getAllWishesByListId(wishlistId);
        model.addAttribute("wishes", wishes);
        model.addAttribute("wishlistId", wishlistId);
        model.addAttribute("userId", service.getUserIdFromWishlist(wishlistId));
        return "open-wishlist";
    }

    //POST-endpoint der sletter et ønske fra en liste.
    //Returnere siden delete-wish.html
    @PostMapping("/{wishId}/delete-wish")
    public String deleteWish(@PathVariable int wishId, Model model) {
        Wish wish = service.findWishById(wishId);
        model.addAttribute("name", wish.getName());
        model.addAttribute("wishlistId", wish.getListId());
        service.deleteWish(wishId);
        return "delete-wish";
    }

}
