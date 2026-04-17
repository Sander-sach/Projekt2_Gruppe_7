package com.example.projekt2_gruppe7.controller;

import com.example.projekt2_gruppe7.model.User;
import com.example.projekt2_gruppe7.model.Wish;
import com.example.projekt2_gruppe7.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class WishController {

    private final WishService wishService;

    @Autowired
    public WishController(WishService wishService) {
        this.wishService = wishService;
    }
    // localhost:8080/createWish?wishlistId=1
    @GetMapping("/createWish")
    public String createWishForm(@RequestParam Long wishlistId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/loginform";
        }

        model.addAttribute("wishlistId", wishlistId);
        return "createWish";
    }

    @PostMapping("/createWish")
    public String createWish(@RequestParam Long wishlistId,
                             @RequestParam String itemName,
                             @RequestParam String description,
                             @RequestParam(required = false) Double price,
                             @RequestParam(required = false) String itemURL,
                             HttpSession session,
                             Model model) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/loginform";
        }

        if (itemName == null || itemName.trim().isEmpty() ||
                description == null || description.trim().isEmpty()) {

            model.addAttribute("error", true);
            model.addAttribute("wishlistId", wishlistId);
            return "createWish";
        }

        Wish wish = new Wish(null, wishlistId, itemName, description, price, itemURL);
        wishService.createWish(wish);

        return "redirect:/wishlist/" + wishlistId;
    }

    //NYT TIL REDIGERING AF ØNSKE

    // GET - vis redigeringssiden for et ønske
    // localhost:8080/editWish?wishId=1
    @GetMapping("/editWish")
    public String editWishForm(@RequestParam Long wishId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/loginform";
        }

        Wish wish = wishService.getWishById(wishId);
        model.addAttribute("wish", wish);
        return "editWish";
    }

    // PUT - opdater navn/titel på et ønske
    // localhost:8080/editWish?wishId=1
    @PostMapping("/editWish")
    public String updateWishName(@RequestParam Long wishId,
                                 @RequestParam String itemName,
                                 HttpSession session,
                                 Model model) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/loginform";
        }

        if (itemName == null || itemName.trim().isEmpty()) {
            Wish wish = wishService.getWishById(wishId);
            model.addAttribute("wish", wish);
            model.addAttribute("error", true);
            return "editWish";
        }

        wishService.updateWishName(wishId, itemName);

        Wish wish = wishService.getWishById(wishId);
        return "redirect:/wishlist/" + wish.getWishListId();
    }
}