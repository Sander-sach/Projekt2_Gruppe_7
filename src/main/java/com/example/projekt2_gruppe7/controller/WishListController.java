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
    public WishListController(WishListService wishListService, WishService wishService) {
        this.wishListService = wishListService;
        this.wishService = wishService;
    }

    // localhost:8080/createWishlist
    @GetMapping("/createWishList")
    public String createWishListForm(HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/loginform";
        }

        return "createWishList";
    }

    @PostMapping("/createWishList")
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
            return "userpage";
        }

        // bruger konstruktør
        WishList wishList = new WishList(null, user.getId(), name, description);

        wishListService.createWishList(wishList);

        // Redirect til brugerens wishlists
        return "redirect:/userpage?userId=" + user.getId();
    }

    @GetMapping("/WishListsCreated")
    public String viewWishList(@RequestParam Long userId, Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
        return "redirect:/loginform";
        }

        List<WishList> wishLists = wishListService.getWishListsByUser(userId);

        model.addAttribute("wishList", wishLists);

        return "userpage";
    }
    @GetMapping("/WishList")
    public String viewWishes(@RequestParam Long wishlistId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/loginform";
        }

        WishList wishList = wishListService.getWishListById(wishlistId);
        List<Wish> wishes = wishService.getWishesByListId(wishlistId);

        model.addAttribute("WishList", wishList);
        model.addAttribute("wishes", wishes);
        model.addAttribute("WishListId", wishlistId);

        return "WishList";
    }
}