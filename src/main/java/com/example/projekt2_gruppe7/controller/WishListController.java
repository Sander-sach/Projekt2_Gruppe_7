package com.example.projekt2_gruppe7.controller;

import com.example.projekt2_gruppe7.model.User;
import com.example.projekt2_gruppe7.model.Wish;
import com.example.projekt2_gruppe7.model.WishList;
import com.example.projekt2_gruppe7.service.WishListService;
import com.example.projekt2_gruppe7.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class WishListController {

    private final WishListService wishListService;
    private final WishService wishService;

    @Autowired
    public WishListController(WishListService wishListService,WishService wishService) {
        this.wishListService = wishListService;
        this.wishService = wishService;
    }

    // localhost:8080/createWishlist
    @GetMapping("/createWishlist")
    public String createWishListForm(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/loginform";
        }

        return "createWishList";
    }

    @PostMapping("/createWishlist")
    public String createWishList(@RequestParam String name,
                                 @RequestParam String description,
                                 HttpSession session,
                                 Model model) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/loginform";
        }

        // Validering (tomme felter afvises)
        if (name == null || name.trim().isEmpty() ||
                description == null || description.trim().isEmpty()) {

            model.addAttribute("error", true);
            return "createWishList";
        }

        // bruger konstruktør
        WishList wishList = new WishList(null, user.getId(), name, description);

        wishListService.createWishList(wishList);

        // Redirect til brugerens wishlists
        return "redirect:/wishlists/" + user.getId();
    }

    @GetMapping("/wishlist")
    public String viewWishList(@RequestParam Long wishlistId, Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
        return "redirect:/loginform";
        }

        WishList wishList = wishListService.getWishListById(wishlistId);
        List<Wish> wishes = wishService.getWishesByListId(wishlistId);

        model.addAttribute("wishList", wishList);
        model.addAttribute("wishes", wishes);
        model.addAttribute("wishListId", wishlistId);

        return "createWishList";
    }
}