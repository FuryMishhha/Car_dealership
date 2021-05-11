package com.example.demo.Controllers;

import com.example.demo.Models.New_car;
import com.example.demo.Models.OrderNewCar;
import com.example.demo.Models.OrderSupportCar;
import com.example.demo.Models.Support_car;
import com.example.demo.Services.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class OrderController {
    @Autowired
    UserService userService;
    @Autowired
    NewCarService newCarService;
    @Autowired
    SupportCarService supportCarService;
    @Autowired
    CriteriaService criteriaService;
    @Autowired
    EmailService emailService;
    @Autowired
    OrderService orderService;

    @RequestMapping(path = "/orders")
    public String orderInfo(Authentication authentication, Model model){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        if (userService.findByName(authentication.getName()).getType().equals("admin")) {
            model.addAttribute("new_orders", orderService.readAllNew());
            model.addAttribute("support_orders", orderService.readAllSupport());
            model.addAttribute("newCarService", newCarService);
            model.addAttribute("supportCarService", supportCarService);
            return "orders";
        } else {
            return "redirect:/home";
        }
    }

    @SneakyThrows
    @PostMapping("/orders")
    public String orderPanel(@Valid Integer id, Authentication authentication, String status, @RequestParam String action, Model model){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        if (action.equals("edit_new_order")){
            if (id!=0){
                OrderNewCar orderNewCar = orderService.findNewOrder(id);
                if (orderNewCar!=null){
                    orderNewCar.setStatus(status);
                    orderService.addNewOrder(orderNewCar);
                }
                else {
                    model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                    model.addAttribute("edit_new_order_error", "Заказа с таким ID нет");
                    model.addAttribute("new_orders", orderService.readAllNew());
                    model.addAttribute("support_orders", orderService.readAllSupport());
                    model.addAttribute("newCarService", newCarService);
                    model.addAttribute("supportCarService", supportCarService);
                    return "orders";
                }
            }
            else {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("edit_new_order_error", "Заказа с таким ID нет");
                model.addAttribute("new_orders", orderService.readAllNew());
                model.addAttribute("support_orders", orderService.readAllSupport());
                model.addAttribute("newCarService", newCarService);
                model.addAttribute("supportCarService", supportCarService);
                return "orders";
            }
        }
        else if (action.equals("edit_support_order")){
            if (id!=0){
                OrderSupportCar orderSupportCar = orderService.findSupportOrder(id);
                if (orderSupportCar!=null){
                    orderSupportCar.setStatus(status);
                    orderService.addSupportOrder(orderSupportCar);
                }
                else {
                    model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                    model.addAttribute("edit_support_order_error", "Заказа с таким ID нет");
                    model.addAttribute("new_orders", orderService.readAllNew());
                    model.addAttribute("support_orders", orderService.readAllSupport());
                    model.addAttribute("newCarService", newCarService);
                    model.addAttribute("supportCarService", supportCarService);
                    return "orders";
                }
            }
            else {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("edit_support_order_error", "Заказа с таким ID нет");
                model.addAttribute("new_orders", orderService.readAllNew());
                model.addAttribute("support_orders", orderService.readAllSupport());
                model.addAttribute("newCarService", newCarService);
                model.addAttribute("supportCarService", supportCarService);
                return "orders";
            }
        }
        else if (action.equals("delete_new_order")){
            if (id!=0){
                OrderNewCar orderNewCar = orderService.findNewOrder(id);
                if (orderNewCar!=null){
                    New_car new_car = newCarService.findNewCar(orderNewCar.getNew_car_id());
                    orderService.deleteNewOrder(orderNewCar.getId());
                    new_car.setOrder_new_car_id(null);
                }
                else {
                    model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                    model.addAttribute("delete_new_order_error", "Заказа с таким ID нет");
                    model.addAttribute("new_orders", orderService.readAllNew());
                    model.addAttribute("support_orders", orderService.readAllSupport());
                    model.addAttribute("newCarService", newCarService);
                    model.addAttribute("supportCarService", supportCarService);
                    return "orders";
                }
            }
            else {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("delete_new_order_error", "Заказа с таким ID нет");
                model.addAttribute("new_orders", orderService.readAllNew());
                model.addAttribute("support_orders", orderService.readAllSupport());
                model.addAttribute("newCarService", newCarService);
                model.addAttribute("supportCarService", supportCarService);
                return "orders";
            }
        }
        else if (action.equals("delete_support_order")){
            if (id!=0){
                OrderSupportCar orderSupportCar = orderService.findSupportOrder(id);
                if (orderSupportCar!=null){
                    Support_car support_car = supportCarService.findSupportCar(orderSupportCar.getSupport_car_id());
                    orderService.deleteSupportOrder(orderSupportCar.getId());
                    support_car.setOrder_support_car_id(null);
                }
                else {
                    model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                    model.addAttribute("delete_support_order_error", "Заказа с таким ID нет");
                    model.addAttribute("new_orders", orderService.readAllNew());
                    model.addAttribute("support_orders", orderService.readAllSupport());
                    model.addAttribute("newCarService", newCarService);
                    model.addAttribute("supportCarService", supportCarService);
                    return "orders";
                }
            }
            else {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("delete_support_order_error", "Заказа с таким ID нет");
                model.addAttribute("new_orders", orderService.readAllNew());
                model.addAttribute("support_orders", orderService.readAllSupport());
                model.addAttribute("newCarService", newCarService);
                model.addAttribute("supportCarService", supportCarService);
                return "orders";
            }
        }
        return "redirect:/orders";
    }

}
