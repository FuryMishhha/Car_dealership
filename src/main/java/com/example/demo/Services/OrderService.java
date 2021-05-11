package com.example.demo.Services;

import com.example.demo.Models.OrderNewCar;
import com.example.demo.Models.OrderSupportCar;
import com.example.demo.Repositories.NewOrderRepository;
import com.example.demo.Repositories.SupportOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderService {
    @Autowired
    private NewOrderRepository newOrderRepository;
    @Autowired
    private SupportOrderRepository supportOrderRepository;

    public void addNewOrder(OrderNewCar orderNewCar){
        log.info("Save order " + orderNewCar.getId());
        newOrderRepository.save(orderNewCar);
    }

    public void addSupportOrder(OrderSupportCar orderSupportCar){
        log.info("Save order " + orderSupportCar.getId());
        supportOrderRepository.save(orderSupportCar);
    }

    public List<OrderNewCar> readAllNew() {
        log.info("Find all new orders");
        List <OrderNewCar> orderNewCars = newOrderRepository.findAll();
        orderNewCars.sort(Comparator.comparingInt(OrderNewCar::getId));
        return orderNewCars;
    }

    public List<OrderSupportCar> readAllSupport() {
        log.info("Find all support orders");
        List <OrderSupportCar> orderSupportCars = supportOrderRepository.findAll();
        orderSupportCars.sort(Comparator.comparingInt(OrderSupportCar::getId));
        return orderSupportCars;
    }

    public OrderNewCar findNewOrder(Integer id){
        log.info("Find new order, whose Id = {}",id);
        return newOrderRepository.findById(id).orElse(null);
    }

    public OrderSupportCar findSupportOrder(Integer id){
        log.info("Find support order, whose Id = {}",id);
        return supportOrderRepository.findById(id).orElse(null);
    }

    public void deleteNewOrder(Integer id){
        log.info("Delete new order, whose Id = {}",id);
        newOrderRepository.deleteOrderById(id);
    }

    public void deleteSupportOrder(Integer id){
        log.info("Delete support order, whose Id = {}",id);
        supportOrderRepository.deleteOrderById(id);
    }
}
