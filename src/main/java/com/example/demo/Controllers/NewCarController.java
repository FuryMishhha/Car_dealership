package com.example.demo.Controllers;

import com.example.demo.Models.New_car;
import com.example.demo.Models.OrderNewCar;
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
import java.util.List;

@Controller
@RequestMapping("/newcars")
public class NewCarController {

    @Autowired
    UserService userService;
    @Autowired
    NewCarService newCarService;
    @Autowired
    CriteriaService criteriaService;
    @Autowired
    EmailService emailService;
    @Autowired
    OrderService orderService;

    @GetMapping
    public String allNewCars(Model model, Authentication authentication){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        model.addAttribute("new_cars", newCarService.readAll());
        return "list_new_cars";
    }

    @PostMapping
    public String showCarDescription(Integer id, String brand, String model1, String body, Model model, Authentication authentication, String action){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        if (action.equals("details")){
            return "redirect:/newcars/"+id;
        }
        else if (action.equals("reset")){
            model.addAttribute("type", userService.findByName(authentication.getName()).getType());
            model.addAttribute("new_cars", newCarService.readAll());
            return "list_new_cars";
        }
        else if (action.equals("search")){
            model.addAttribute("type", userService.findByName(authentication.getName()).getType());
            if (brand.equals("") && !model1.equals("") && !body.equals("")){
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("new_cars", criteriaService.takeByWithoutBrand(model1, body));
                return "list_new_cars";
            }
            else if (!brand.equals("") && model1.equals("") && !body.equals("")){
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("new_cars", criteriaService.takeByWithoutModel(brand, body));
                return "list_new_cars";
            }
            else if (!brand.equals("") && !model1.equals("") && body.equals("")){
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("new_cars", criteriaService.takeByWithoutBody(brand, model1));
                return "list_new_cars";
            }

            else if (!brand.equals("") && model1.equals("") && body.equals("")){
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("new_cars", criteriaService.takeByBrand(brand));
                return "list_new_cars";
            }
            else if (brand.equals("") && !model1.equals("") && body.equals("")){
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("new_cars", criteriaService.takeByModel(model1));
                return "list_new_cars";
            }
            else if (brand.equals("") && model1.equals("") && !body.equals("")){
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("new_cars", criteriaService.takeByBody(body));
                return "list_new_cars";
            }

            else if (!brand.equals("") && !model1.equals("") && !body.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("new_cars", criteriaService.takeByAllNew(brand, model1, body));
                return "list_new_cars";
            }
            else if (brand.equals("") && model1.equals("") && body.equals("")) {
                model.addAttribute("type", userService.findByName(authentication.getName()).getType());
                model.addAttribute("no_car_error", "Все поля поиска пусты");
                return "list_new_cars";
            }
        }
        return "redirect:/newcars";
    }

    @RequestMapping("admin_list_new_cars")
    public String allNewCarsAdmin(Model model, Authentication authentication){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        if (userService.findByName(authentication.getName()).getType().equals("admin")) {
            model.addAttribute("new_cars", newCarService.readAll());
            return "admin_list_new_cars";
        } else {
            return "redirect:/newcars";
        }
    }

    @GetMapping("{id}")
    public String OneCarDescription(@PathVariable Integer id, Model model, Authentication authentication){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        New_car new_car = newCarService.findNewCar(id);
        if (new_car != null) {
            if (newCarService.findNewCar(id).getOrder_new_car_id()==null) {
                model.addAttribute("brand", new_car.getBrand());
                model.addAttribute("model", new_car.getModel());
                model.addAttribute("release_year", new_car.getRelease_year());
                model.addAttribute("body", new_car.getBody());
                model.addAttribute("color", new_car.getColor());
                model.addAttribute("engine", new_car.getEngine());
                model.addAttribute("drive", new_car.getDrive());
                model.addAttribute("wheel", new_car.getWheel());
                model.addAttribute("price", new_car.getPrice());
                model.addAttribute("picture", new_car.getPicture());
                return "new_car";
            } else {
                return "redirect:/newcars";
            }
        }
        else {
            return "redirect:/newcars";
        }
    }

    @SneakyThrows
    @PostMapping("{id}")
    public String NewOrder(@PathVariable Integer id, Model model, Authentication authentication){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        New_car new_car = newCarService.findNewCar(id);
        if (new_car!=null){
            OrderNewCar orderNewCar = new OrderNewCar();
            orderNewCar.setUser1(userService.findByName(authentication.getName()));
            orderNewCar.setNew_car_id(id);
            orderNewCar.setStatus("Резерв");

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            orderNewCar.setCreation_date(dateFormat.format(date));

            orderService.addNewOrder(orderNewCar);

            new_car.setOrder_new_car_id(orderNewCar.getId());
            newCarService.addNewCar(new_car);

            StringBuilder message = new StringBuilder();
            message.append(new_car.toString());
            emailService.sendmail(message.toString(), userService.findByName(authentication.getName()).getEmail());

            return "redirect:/account";
        }
        else{
            return "redirect:/newcars";
        }
    }

    public String parse(String url){
        String res="https://drive.google.com/uc?export=view&id="+url.substring(("https://drive.google.com/file/d/").length(),url.length()-"/view?usp=sharing".length());
        return res;
    }

    @PostMapping("admin_list_new_cars")
    public String allNewCarsAdmin1(@Valid New_car new_carX, String action, Model model, Authentication authentication){
        model.addAttribute("type", userService.findByName(authentication.getName()).getType());
        if (action.equals("edit")){
            New_car new_car = newCarService.findNewCar(new_carX.getId());
            if (new_car!=null) {
                new_carX.setId(new_carX.getId());
                if (!new_carX.getBrand().equals(""))
                    new_car.setBrand(new_carX.getBrand());
                if (!new_carX.getModel().equals(""))
                    new_car.setModel(new_carX.getModel());
                if (!new_carX.getRelease_year().equals(""))
                    new_car.setRelease_year(new_carX.getRelease_year());
                if (!new_carX.getBody().equals(""))
                    new_car.setBody(new_carX.getBody());
                if (!new_carX.getColor().equals(""))
                    new_car.setColor(new_carX.getColor());
                if (!new_carX.getEngine().equals(""))
                    new_car.setEngine(new_carX.getEngine());
                if (!new_carX.getDrive().equals(""))
                    new_car.setDrive(new_carX.getDrive());
                if (!new_carX.getWheel().equals(""))
                    new_car.setWheel(new_carX.getWheel());
                if (!new_carX.getPrice().equals(""))
                    new_car.setPrice(new_carX.getPrice());
                if (!new_carX.getPicture().equals(""))
                    new_car.setPicture(parse(new_carX.getPicture()));
                newCarService.addNewCar(new_car);
            }
            else {
                model.addAttribute("edit_error", "Автомобиля с таким ID нет");
                model.addAttribute("new_cars", newCarService.readAll());
                return "admin_list_new_cars";
            }
        }
        else if (action.equals("create")){
            if (new_carX.getBrand().equals("") || new_carX.getModel().equals("") ||
                    new_carX.getRelease_year().equals("") || new_carX.getBody().equals("") ||
                    new_carX.getColor().equals("") || new_carX.getEngine().equals("") ||
                    new_carX.getDrive().equals("") || new_carX.getWheel().equals("") ||
                    new_carX.getPrice().equals("") || new_carX.getPicture().equals("")){
                model.addAttribute("create_error", "Вы заполнили не все поля");
                model.addAttribute("new_cars", newCarService.readAll());
                return "admin_list_new_cars";
            }
            New_car new_car = new New_car();
            new_car.setBrand(new_carX.getBrand());
            new_car.setModel(new_carX.getModel());
            new_car.setRelease_year(new_carX.getRelease_year());
            new_car.setBody(new_carX.getBody());
            new_car.setColor(new_carX.getColor());
            new_car.setEngine(new_carX.getEngine());
            new_car.setDrive(new_carX.getDrive());
            new_car.setWheel(new_carX.getWheel());
            new_car.setPrice(new_carX.getPrice());
            new_car.setPicture(parse(new_carX.getPicture()));
            newCarService.addNewCar(new_car);
        }
        else if (action.equals("delete")) {
            if (new_carX.getId()!=0) {
                New_car new_car = newCarService.findNewCar(new_carX.getId());
                if (new_car != null) {
                    if (new_car.getOrder_new_car_id()!=null) {
                        orderService.deleteNewOrder(new_car.getOrder_new_car_id());
                        newCarService.deleteNewCar(new_carX.getId());
                    }
                    else {
                        newCarService.deleteNewCar(new_carX.getId());
                    }
                }
                else {
                    model.addAttribute("delete_error", "Автомобиля с таким ID нет");
                    model.addAttribute("new_cars", newCarService.readAll());
                    return "admin_list_new_cars";
                }
            }
            else {
                model.addAttribute("delete_error", "Автомобиля с таким ID нет");
                model.addAttribute("new_cars", newCarService.readAll());
                return "admin_list_new_cars";
            }
        }
        return "redirect:/newcars/admin_list_new_cars";
    }
}