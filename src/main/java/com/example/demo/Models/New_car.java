package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "new_cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class New_car implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "release_year")
    private String release_year;
    @Column(name = "body")
    private String body;
    @Column(name = "color")
    private String color;
    @Column(name = "engine")
    private String engine;
    @Column(name = "drive")
    private String drive;
    @Column(name = "wheel")
    private String wheel;
    @Column(name = "price")
    private String price;
    @Column(name = "picture")
    private String picture;
    @Column(name = "order_new_car_id")
    private Integer order_new_car_id;

    @Override
    public String toString() {
        return "Автомобиль: " + brand + " " + model + ", Номер заказа: " + order_new_car_id;
    }
}