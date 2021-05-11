package com.example.demo.Controllers;

import com.example.demo.Models.OrderNewCar;
import com.example.demo.Services.NewCarService;
import com.example.demo.Services.OrderService;
import com.example.demo.Services.SupportCarService;
import com.example.demo.Services.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private NewCarService newCarService;
    @Autowired
    private SupportCarService supportCarService;

    @RequestMapping(path = "/account")
    public String myinfo(Authentication authentication, Model model) {
        model.addAttribute("id", userService.findByName(authentication.getName()).getId());
        model.addAttribute("username", authentication.getName());
        model.addAttribute("email", userService.findByName(authentication.getName()).getEmail());
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        model.addAttribute("new_orders", orderService.readAllNew());
        model.addAttribute("support_orders", orderService.readAllSupport());
        model.addAttribute("newCarService", newCarService);
        model.addAttribute("supportCarService", supportCarService);
        return "account";
    }

    @SneakyThrows
    @PostMapping(path = "/account")
    public String changeEmail(Authentication authentication, String email, @RequestParam String action, Model model) {
        if ("change_email".equals(action)) {
            if (!email.equals("")) {
                userService.findByName(authentication.getName()).setEmail(email);
                userService.saveChanges(userService.findByName(authentication.getName()));
            }
            else {
                model.addAttribute("id", userService.findByName(authentication.getName()).getId());
                model.addAttribute("username", authentication.getName());
                model.addAttribute("email", userService.findByName(authentication.getName()).getEmail());
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("new_orders", orderService.readAllNew());
                model.addAttribute("support_orders", orderService.readAllSupport());
                model.addAttribute("newCarService", newCarService);
                model.addAttribute("supportCarService", supportCarService);
                model.addAttribute("edit_error", "Вы ввели пустую строку");
                return "account";
            }
        }
        return "redirect:/account";
    }

    @RequestMapping(path = "/admin")
    public String admin(Authentication authentication, Model model) {
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        if (userService.findByName(authentication.getName()).getType().equals("admin")) {
            return "admin";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(path = "/users")
    public String Usersinfo(Authentication authentication, Model model){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        if (userService.findByName(authentication.getName()).getType().equals("admin")) {
            model.addAttribute("users", userService.readAll());
            return "users";
        } else {
            return "redirect:/home";
        }
    }

    @SneakyThrows
    @PostMapping(path = "/users")
    public String userPanel(Authentication authentication, String name, String type, @RequestParam String action, Model model){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        if (action.equals("change_type")) {
            if (userService.findByName(name)!=null) {
                userService.findByName(name).setType(type);
                userService.saveChanges(userService.findByName(name));
                return "redirect:/users";
            }
            else {
                model.addAttribute("edit_error", "Пользователя с таким именем нет");
                model.addAttribute("users", userService.readAll());
                return "users";
            }
        }
        else if (action.equals("delete_user")) {
            if (userService.findByName(name)!=null) {
                userService.deleteUser(name);
                return "redirect:/users";
            }
            else {
                model.addAttribute("delete_error", "Пользователя с таким именем нет");
                model.addAttribute("users", userService.readAll());
                return "users";
            }
        }
        return "redirect:/users";
    }

    @GetMapping("/sign")
    public String index() {
        return "signup";
    }

    @RequestMapping(path = "/sign", method = RequestMethod.POST)
    public String SignUp(@RequestParam String username, String password, String password2, String email, String type,
                         Model model) {
        if (!password.equals(password2)) {
            model.addAttribute("Status", "pass1!=pass2");
            return "signup";
        } else {
            if (userService.loadUserByUsername(username) != null) {
                model.addAttribute("Status", "user_exists");
                return "signup";
            } else {
                userService.create(username, password, email, type);
                return "redirect:/logout";
            }
        }
    }
}