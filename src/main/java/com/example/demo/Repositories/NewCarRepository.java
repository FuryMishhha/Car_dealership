package com.example.demo.Repositories;

import com.example.demo.Models.New_car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewCarRepository extends JpaRepository <New_car, Integer> {
    Long deleteNew_carById(Integer id);
    New_car findNew_carById(Integer id);
}
