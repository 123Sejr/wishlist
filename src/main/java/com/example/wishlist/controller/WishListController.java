package com.example.wishlist.controller;

import com.example.wishlist.model.Wishlist;
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
    //Returnerer html-siden "show-wishlists".
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


    //GET-endpointet, der giver brugeren lov til at omdøbe navnet på den specifik liste.
    //Returnere html-siden "edit-wishlist-name".
    @GetMapping("/{wishlistId}/edit-wishlist-name")
    public String editWishlistName(@PathVariable int wishlistId, Model model) {
        Wishlist wishlist = service.findWishListById(wishlistId);
        model.addAttribute("wishlist", wishlist);
        return "edit-wishlist-name";
    }

    //POST-endpointet, der gemmer det nye navn på ønskeliste fra GET-endpointet "/{wishlistId}/edit-wishlist-name".
    //Returnere html-siden update-wishlist-name
    @PostMapping("/update-wishlist-name")
    public String updateWishlistName(Model model, @ModelAttribute("wishlist") Wishlist wishlist) {
        System.out.println(wishlist.getId());
        model.addAttribute("wishlist", wishlist);
        service.editWishlistName(wishlist);
        return "update-wishlist-name";
    }



    //POST-endpoint, der sletter en hel liste og alle dens ønsker.
    //Returnere siden delete-list.html
    @PostMapping("/{wishlistId}/delete-list")
    public String deleteList(@PathVariable int wishlistId, Model model) {
        Wishlist wishlist = service.findWishListById(wishlistId);
        model.addAttribute("name", wishlist.getName());
        model.addAttribute("userId", wishlist.getUser_id());
        service.deleteWishlist(wishlistId);
        return "delete-list";
    }

}
