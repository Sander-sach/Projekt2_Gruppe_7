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
// test
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

        // Validering (Acceptance criteria: tomme felter afvises)
        if (itemName == null || itemName.trim().isEmpty() ||
                description == null || description.trim().isEmpty()) {

            model.addAttribute("error", true);
            model.addAttribute("wishlistId", wishlistId);
            return "createWish";
        }

        // bruger konstruktør (ingen setter til wishListId!)
        Wish wish = new Wish(null, wishlistId, itemName, description, price, itemURL);

        wishService.createWish(wish);

        // Redirect tilbage til wishlist (så ønsket vises)
        return "redirect:/wishlist/" + wishlistId;
    }
}
