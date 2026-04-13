package com.example.projekt2_gruppe7.controller;

import com.example.projekt2_gruppe7.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.projekt2_gruppe7.service.UserService;


@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
    this.userService = userService;
}

    @GetMapping("/frontpage/register")
        public String registerForm(){
        return "register";
    }

    @PostMapping("/frontpage/register")
    public String registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        userService.registerUser(name, email, password);
        return "redirect:/frontpage";
    }
    @GetMapping("/frontpage/loginform/")
    public String loginForm(Model model){

        return "loginform";
    }

    @PostMapping("/frontpage/loginform/")
    public String userLogin(@RequestParam String email, @RequestParam String password, Model model, HttpSession session){
        User user = userService.getUser(email, password);

        if(user != null) {
            session.setAttribute("user", user);
            return"redirect:/userpage";
        }
        //login error model for Thymeleaf view
        model.addAttribute("error", true);
    return "loginform";
    }

    @GetMapping("/userpage")
    public String userPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");

        //if() stops direct URL login with no user loaded in session.
        if (user == null) {
            model.addAttribute("error", true);
            return "loginform";
        }
        model.addAttribute("user", user);

    return "userpage";
    }
}
