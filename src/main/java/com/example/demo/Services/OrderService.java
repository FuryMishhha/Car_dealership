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
/**
 * Класс OrderService, который отвечает за бизнес-логику работы с заказами.
 * Имеет два приватных свойства - newOrderRepository и supportOrderRepository
 */
@Slf4j
@Service
@Transactional
public class OrderService {

    /** Репозиторий, необходимый для работы с заказов новых авто*/
    @Autowired
    private NewOrderRepository newOrderRepository;

    /** Репозиторий, необходимый для работы с заказов подержанных авто*/
    @Autowired
    private SupportOrderRepository supportOrderRepository;

    /**
     * Метод добавления заказа нового авто
     * @param orderNewCar заказ нового авто
     */
    public void addNewOrder(OrderNewCar orderNewCar){
        log.info("Save order " + orderNewCar.getId());
        newOrderRepository.save(orderNewCar);
    }

    /**
     * Метод добавления заказа подержанного авто
     * @param orderSupportCar заказ подержанного авто
     */
    public void addSupportOrder(OrderSupportCar orderSupportCar){
        log.info("Save order " + orderSupportCar.getId());
        supportOrderRepository.save(orderSupportCar);
    }

    /**
     * Метод вывода всех заказов новых авто
     * @return список заказов новых авто
     */
    public List<OrderNewCar> readAllNew() {
        log.info("Find all new orders");
        List <OrderNewCar> orderNewCars = newOrderRepository.findAll();
        orderNewCars.sort(Comparator.comparingInt(OrderNewCar::getId));
        return orderNewCars;
    }

    /**
     * Метод вывода всех заказов подержанных авто
     * @return список заказов подержанных авто
     */
    public List<OrderSupportCar> readAllSupport() {
        log.info("Find all support orders");
        List <OrderSupportCar> orderSupportCars = supportOrderRepository.findAll();
        orderSupportCars.sort(Comparator.comparingInt(OrderSupportCar::getId));
        return orderSupportCars;
    }

    /**
     * Метод поиска заказа нового авто
     * @param id номер заказа нового авто
     * @return заказ нового авто
     */
    public OrderNewCar findNewOrder(Integer id){
        log.info("Find new order, whose Id = {}",id);
        return newOrderRepository.findById(id).orElse(null);
    }

    /**
     * Метод поиска заказа подержанного авто
     * @param id номер заказа подержанного авто
     * @return заказ подержанного авто
     */
    public OrderSupportCar findSupportOrder(Integer id){
        log.info("Find support order, whose Id = {}",id);
        return supportOrderRepository.findById(id).orElse(null);
    }

    /**
     * Метод удаления заказа нового авто
     * @param id номер заказа нового авто
     */
    public void deleteNewOrder(Integer id){
        log.info("Delete new order, whose Id = {}",id);
        newOrderRepository.deleteOrderById(id);
    }

    /**
     * Метод удаления заказа подержанного авто
     * @param id номер заказа подержанного авто
     */
    public void deleteSupportOrder(Integer id){
        log.info("Delete support order, whose Id = {}",id);
        supportOrderRepository.deleteOrderById(id);
    }
}
