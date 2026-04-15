package com.example.projekt2_gruppe7.Controller;

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
    // http://localhost:8080/frontpage/register (Hvis man skal åbne hjemmesiden skal main køre og dette skrives i browseren.

    @GetMapping("/register")
        public String registerForm(){
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        userService.registerUser(name, email, password);
        return "redirect:/loginform";
    }
    //localhost:8080/frontpage/loginform for at åbne siden.
    @GetMapping("/loginform")
    public String loginForm(Model model){

        return "loginform";
    }

    @PostMapping("/loginform")
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
    //locashost:8080/userpage
    @GetMapping("/userpage")
    public String userPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");

        model.addAttribute("user", user);

    return "userpage";
    }
    // til når vi får gang i en frontpage
    @GetMapping("/")
    public String frontpage() {
        return "frontpage";
    }
}
