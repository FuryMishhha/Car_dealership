package com.example.demo.Repositories;

import com.example.demo.Models.New_car;
import com.example.demo.Models.Support_car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportCarRepository extends JpaRepository <Support_car, Integer> {
    Long deleteSupport_carById(Integer id);
    Support_car findSupport_carById(Integer id);
}
