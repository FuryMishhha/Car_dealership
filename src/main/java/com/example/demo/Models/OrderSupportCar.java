package com.example.demo.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders_support")
@Getter
@Setter
public class OrderSupportCar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user2;

    @Column(name = "status")
    private String status;

    @Column(name = "support_car_id")
    private Integer support_car_id;

    @Column(name = "creation_date")
    private String creation_date;
}
