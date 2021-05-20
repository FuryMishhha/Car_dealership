package com.example.demo.Repositories;

import com.example.demo.Models.OrderNewCar;
import com.example.demo.Models.OrderSupportCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportOrderRepository extends JpaRepository <OrderSupportCar, Integer> {
    Long deleteOrderById(Integer id);
    OrderSupportCar findOrderSupportCarById(Integer id);
}
