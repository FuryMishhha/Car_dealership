package com.example.demo.Controllers;

import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @RequestMapping("/home")
    public String home(Authentication authentication, Model model){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        return "home";
    }
}
