package com.example.demo.Controllers;

import com.example.demo.Models.OrderSupportCar;
import com.example.demo.Models.Support_car;
import com.example.demo.Services.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/supportcars")
public class SupportCarController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    SupportCarService supportCarService;
    @Autowired
    CriteriaService criteriaService;
    @Autowired
    EmailService emailService;

    @GetMapping
    public String allSupportCars(Authentication authentication, Model model){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        model.addAttribute("support_cars", supportCarService.readAll());
        return "list_support_cars";
    }

    @PostMapping
    public String showCarDescription(Integer id, String brand, String model1, String body, String year, Model model, Authentication authentication, String action){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        if (action.equals("details")){
            return "redirect:/supportcars/"+id;
        }
        else if (action.equals("reset")){
            model.addAttribute("type", userService.findByName(authentication.getName()).getType());
            model.addAttribute("support_cars", supportCarService.readAll());
            return "list_support_cars";
        }
        else if (action.equals("search")) {
            model.addAttribute("type", userService.findByName(authentication.getName()).getType());
            if (brand.equals("") && !model1.equals("") && !body.equals("") && !year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByWithoutBrandSup(model1, body, year));
                return "list_support_cars";
            }
            else if (!brand.equals("") && model1.equals("") && !body.equals("") && !year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByWithoutModelSup(brand, body, year));
                return "list_support_cars";
            }
            else if (!brand.equals("") && !model1.equals("") && body.equals("") && !year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByWithoutBodySup(brand, model1, year));
                return "list_support_cars";
            }
            else if (!brand.equals("") && !model1.equals("") && !body.equals("") && year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByWithoutYearSup(brand, model1, body));
                return "list_support_cars";
            }
            else if (brand.equals("") && model1.equals("") && !body.equals("") && !year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByWithoutBrandModel(body, year));
                return "list_support_cars";
            }
            else if (brand.equals("") && !model1.equals("") && body.equals("") && !year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByWithoutBrandBody(model1, year));
                return "list_support_cars";
            }
            else if (brand.equals("") && !model1.equals("") && !body.equals("") && year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByWithoutBrandYear(model1, body));
                return "list_support_cars";
            }
            else if (!brand.equals("") && model1.equals("") && body.equals("") && !year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByWithoutModelBody(brand, year));
                return "list_support_cars";
            }
            else if (!brand.equals("") && model1.equals("") && !body.equals("") && year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByWithoutModelYear(brand, body));
                return "list_support_cars";
            }
            else if (!brand.equals("") && !model1.equals("") && body.equals("") && year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByWithoutBodyYear(brand, model1));
                return "list_support_cars";
            }
            else if (!brand.equals("") && model1.equals("") && body.equals("") && year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByBrandSup(brand));
                return "list_support_cars";
            }
            else if (brand.equals("") && !model1.equals("") && body.equals("") && year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByModelSup(model1));
                return "list_support_cars";
            }
            else if (brand.equals("") && model1.equals("") && !body.equals("") && year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByBodySup(body));
                return "list_support_cars";
            }
            else if (brand.equals("") && model1.equals("") && body.equals("") && !year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByYear(year));
                return "list_support_cars";
            }
            else if (!brand.equals("") && !model1.equals("") && !body.equals("") && !year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("support_cars", criteriaService.takeByAllSup(brand, model1, body, year));
                return "list_support_cars";
            }
            else if (brand.equals("") && model1.equals("") && body.equals("") && year.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("no_car_error", "Все поля поиска пусты");
                return "list_support_cars";
            }
        }
        return "redirect:/supportcars";
    }

    @RequestMapping("admin_list_support_cars")
    public String allSupportCarsAdmin(Authentication authentication, Model model){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        if (userService.findByName(authentication.getName()).getType().equals("admin")) {
            model.addAttribute("support_cars", supportCarService.readAll());
            return "admin_list_support_cars";
        } else {
            return "redirect:/supportcars";
        }
    }

    @GetMapping("{id}")
    public String oneCarDescription(@PathVariable Integer id, Model model, Authentication authentication){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        Support_car support_car = supportCarService.findSupportCar(id);
        if (support_car!=null){
            if (supportCarService.findSupportCar(id).getOrder_support_car_id()==null) {
                model.addAttribute("brand", support_car.getBrand());
                model.addAttribute("model", support_car.getModel());
                model.addAttribute("mileage", support_car.getMileage());
                model.addAttribute("release_year", support_car.getRelease_year());
                model.addAttribute("body", support_car.getBody());
                model.addAttribute("color", support_car.getColor());
                model.addAttribute("engine", support_car.getEngine());
                model.addAttribute("drive", support_car.getDrive());
                model.addAttribute("wheel", support_car.getWheel());
                model.addAttribute("number_of_owners", support_car.getNumber_of_owners());
                model.addAttribute("price", support_car.getPrice());
                model.addAttribute("picture", support_car.getPicture());
                return "support_car";
            }
            else {
                return "redirect:/supportcars";
            }
        }
        else{
            return "redirect:/supportcars";
        }
    }

    @SneakyThrows
    @PostMapping("{id}")
    public String supportOrder(@PathVariable Integer id, Model model, Authentication authentication){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        Support_car support_car = supportCarService.findSupportCar(id);
        if (support_car!=null){
            OrderSupportCar orderSupportCar = new OrderSupportCar();
            orderSupportCar.setUser2(userService.findByName(authentication.getName()));
            orderSupportCar.setSupport_car_id(id);
            orderSupportCar.setStatus("Резерв");

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            orderSupportCar.setCreation_date(dateFormat.format(date));

            orderService.addSupportOrder(orderSupportCar);

            support_car.setOrder_support_car_id(orderSupportCar.getId());
            supportCarService.addSupportCar(support_car);

            StringBuilder message = new StringBuilder();
            message.append(support_car.toString());
            emailService.sendmail(message.toString(), userService.findByName(authentication.getName()).getEmail());

            return "redirect:/account";
        }
        else{
            return "redirect:/supportcars";
        }
    }

    public String parse(String url){
        String res="https://drive.google.com/uc?export=view&id="+url.substring(("https://drive.google.com/file/d/").length(),url.length()-"/view?usp=sharing".length());
        return res;
    }

    @PostMapping("admin_list_support_cars")
    public String allSupportCarsAdmin1(@Valid Support_car support_carX, String action, Model model, Authentication authentication){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        if (action.equals("edit")){
            Support_car support_car = supportCarService.findSupportCar(support_carX.getId());
            if (support_car!=null) {
                support_carX.setId(support_carX.getId());
                if (!support_carX.getBrand().equals(""))
                    support_car.setBrand(support_carX.getBrand());
                if (!support_carX.getModel().equals(""))
                    support_car.setModel(support_carX.getModel());
                if (!support_carX.getMileage().equals(""))
                    support_car.setMileage(support_carX.getMileage());
                if (!support_carX.getRelease_year().equals(""))
                    support_car.setRelease_year(support_carX.getRelease_year());
                if (!support_carX.getBody().equals(""))
                    support_car.setBody(support_carX.getBody());
                if (!support_carX.getColor().equals(""))
                    support_car.setColor(support_carX.getColor());
                if (!support_carX.getEngine().equals(""))
                    support_car.setEngine(support_carX.getEngine());
                if (!support_carX.getDrive().equals(""))
                    support_car.setDrive(support_carX.getDrive());
                if (!support_carX.getWheel().equals(""))
                    support_car.setWheel(support_carX.getWheel());
                if (!support_carX.getNumber_of_owners().equals(""))
                    support_car.setNumber_of_owners(support_carX.getNumber_of_owners());
                if (!support_carX.getPrice().equals(""))
                    support_car.setPrice(support_carX.getPrice());
                if (!support_carX.getPicture().equals(""))
                    support_car.setPicture(parse(support_carX.getPicture()));
                supportCarService.addSupportCar(support_car);
            }
            else {
                model.addAttribute("edit_error", "Автомобиля с таким ID нет");
                model.addAttribute("support_cars", supportCarService.readAll());
                return "admin_list_support_cars";
            }
        }
        else if (action.equals("create")){
            if (support_carX.getBrand().equals("") || support_carX.getModel().equals("") ||
                    support_carX.getRelease_year().equals("") || support_carX.getBody().equals("") ||
                    support_carX.getMileage().equals("") || support_carX.getColor().equals("") ||
                    support_carX.getEngine().equals("") || support_carX.getDrive().equals("") ||
                    support_carX.getWheel().equals("") || support_carX.getNumber_of_owners().equals("") ||
                    support_carX.getPrice().equals("") || support_carX.getPicture().equals("")){
                model.addAttribute("create_error", "Вы заполнили не все поля");
                model.addAttribute("support_cars", supportCarService.readAll());
                return "admin_list_support_cars";
            }
            Support_car support_car = new Support_car();
            support_car.setBrand(support_carX.getBrand());
            support_car.setModel(support_carX.getModel());
            support_car.setMileage(support_carX.getMileage());
            support_car.setRelease_year(support_carX.getRelease_year());
            support_car.setBody(support_carX.getBody());
            support_car.setColor(support_carX.getColor());
            support_car.setEngine(support_carX.getEngine());
            support_car.setDrive(support_carX.getDrive());
            support_car.setWheel(support_carX.getWheel());
            support_car.setPrice(support_carX.getPrice());
            support_car.setNumber_of_owners(support_carX.getNumber_of_owners());
            support_car.setPicture(parse(support_carX.getPicture()));
            supportCarService.addSupportCar(support_car);
        }
        else if (action.equals("delete")) {
            if (support_carX.getId()!=0) {
                Support_car support_car = supportCarService.findSupportCar(support_carX.getId());
                if (support_car != null) {
                    if (support_car.getOrder_support_car_id()!=null) {
                        orderService.deleteSupportOrder(support_car.getOrder_support_car_id());
                        supportCarService.deleteSupportCar(support_carX.getId());
                    }
                    else {
                        supportCarService.deleteSupportCar(support_carX.getId());
                    }
                }
                else {
                    model.addAttribute("delete_error", "Автомобиля с таким ID нет");
                    model.addAttribute("support_cars", supportCarService.readAll());
                    return "admin_list_support_cars";
                }
            }
            else {
                model.addAttribute("delete_error", "Автомобиля с таким ID нет");
                model.addAttribute("support_cars", supportCarService.readAll());
                return "admin_list_support_cars";
            }
        }
        return "redirect:/supportcars/admin_list_support_cars";
    }
}
