package com.example.projekt2_gruppe7.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.projekt2_gruppe7.service.UserService;

@Controller
public class RegisterController {


private final UserService userService;

public RegisterController(UserService userService){
this.userService = userService;
}
@GetMapping("/register")
    public String showRegister(Model model){
    model.addAttribute("userDTO");
    return "register";

}
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("userDTO")
                                 BindingResult result,
                                 Model model) {
    if (result.hasErrors()){
        return "register";
    }
    try {
        userService.registerUser();
        return "redirect:/login";
    }catch (Exception e){
        model.addAttribute("errorMessage",e.getMessage());
        return "register";
    }

}

}
