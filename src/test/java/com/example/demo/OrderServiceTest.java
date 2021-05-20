package com.example.demo;

import com.example.demo.Models.New_car;
import com.example.demo.Models.OrderNewCar;
import com.example.demo.Models.OrderSupportCar;
import com.example.demo.Models.User;
import com.example.demo.Repositories.NewCarRepository;
import com.example.demo.Repositories.NewOrderRepository;
import com.example.demo.Repositories.SupportOrderRepository;
import com.example.demo.Services.NewCarService;
import com.example.demo.Services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;
    @Mock
    private NewOrderRepository newOrderRepository;
    @Mock
    private SupportOrderRepository supportOrderRepository;
    @Captor
    private ArgumentCaptor<OrderNewCar> captor1;
    @Captor
    private ArgumentCaptor<OrderSupportCar> captor2;

    private OrderNewCar new_order_1, new_order_2, new_order_3;

    private OrderSupportCar support_order_1, support_order_2, support_order_3;

    private User user1, user2, user3;

    @BeforeEach
    void init() {
        user1 = new User();
        user1.setId(1);
        user1.setName("user1");
        user1.setPassword("password1");
        user1.setEmail("email1");
        user1.setType("user");

        user2 = new User();
        user2.setId(2);
        user2.setName("user2");
        user2.setPassword("password2");
        user2.setEmail("email2");
        user2.setType("user");

        user3 = new User();
        user3.setId(3);
        user3.setName("user3");
        user3.setPassword("password3");
        user3.setEmail("email3");
        user3.setType("user");

        new_order_1 = new OrderNewCar();
        new_order_1.setId(1);
        new_order_1.setUser1(user1);
        new_order_1.setNew_car_id(1);
        new_order_1.setStatus("status1");
        new_order_1.setCreation_date("creation_date1");

        new_order_2 = new OrderNewCar();
        new_order_2.setId(2);
        new_order_2.setUser1(user2);
        new_order_2.setNew_car_id(2);
        new_order_2.setStatus("status2");
        new_order_2.setCreation_date("creation_date2");

        new_order_3 = new OrderNewCar();
        new_order_3.setId(3);
        new_order_3.setUser1(user3);
        new_order_3.setNew_car_id(3);
        new_order_3.setStatus("status3");
        new_order_3.setCreation_date("creation_date3");

        support_order_1 = new OrderSupportCar();
        support_order_1.setId(1);
        support_order_1.setUser2(user1);
        support_order_1.setSupport_car_id(1);
        support_order_1.setStatus("status1");
        support_order_1.setCreation_date("creation_date1");

        support_order_2 = new OrderSupportCar();
        support_order_2.setId(2);
        support_order_2.setUser2(user2);
        support_order_2.setSupport_car_id(2);
        support_order_2.setStatus("status2");
        support_order_2.setCreation_date("creation_date2");

        support_order_3 = new OrderSupportCar();
        support_order_3.setId(3);
        support_order_3.setUser2(user3);
        support_order_3.setSupport_car_id(3);
        support_order_3.setStatus("status3");
        support_order_3.setCreation_date("creation_date3");
    }

    @Test
    void addNewOrder() {
        orderService.addNewOrder(new_order_2);
        Mockito.verify(newOrderRepository).save(captor1.capture());
        OrderNewCar captured = captor1.getValue();
        assertEquals(user2, captured.getUser1());
    }

    @Test
    void addSupportOrder() {
        orderService.addSupportOrder(support_order_2);
        Mockito.verify(supportOrderRepository).save(captor2.capture());
        OrderSupportCar captured = captor2.getValue();
        assertEquals(user2, captured.getUser2());
    }

    @Test
    void findNewOrder() {
        Mockito.when(newOrderRepository.findOrderNewCarById(1)).thenReturn(new_order_1);
        assertEquals(new_order_1, newOrderRepository.findOrderNewCarById(1));
    }

    @Test
    void findSupportOrder() {
        Mockito.when(supportOrderRepository.findOrderSupportCarById(1)).thenReturn(support_order_1);
        assertEquals(support_order_1, supportOrderRepository.findOrderSupportCarById(1));
    }

    @Test
    void readAllNew() {
        ArrayList<OrderNewCar> orderNewCars = new ArrayList<>();
        orderNewCars.add(new_order_1);
        orderNewCars.add(new_order_2);
        orderNewCars.add(new_order_3);
        Mockito.when(newOrderRepository.findAll()).thenReturn(orderNewCars);
        assertEquals(orderNewCars, newOrderRepository.findAll());
    }

    @Test
    void readAllSupport() {
        ArrayList<OrderSupportCar> orderSupportCars = new ArrayList<>();
        orderSupportCars.add(support_order_1);
        orderSupportCars.add(support_order_2);
        orderSupportCars.add(support_order_3);
        Mockito.when(supportOrderRepository.findAll()).thenReturn(orderSupportCars);
        assertEquals(orderSupportCars, supportOrderRepository.findAll());
    }

    @Test
    void deleteNewOrder() {
        orderService.deleteNewOrder(2);
        Mockito.verify(newOrderRepository).deleteOrderById(2);
    }

    @Test
    void deleteSupportOrder() {
        orderService.deleteSupportOrder(2);
        Mockito.verify(supportOrderRepository).deleteOrderById(2);
    }
}
