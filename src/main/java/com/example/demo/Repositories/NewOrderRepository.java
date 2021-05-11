package com.example.demo.Repositories;

import com.example.demo.Models.OrderNewCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewOrderRepository extends JpaRepository <OrderNewCar, Integer> {
    Long deleteOrderById(Integer id);
}
