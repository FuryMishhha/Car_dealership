package com.example.demo.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders_new")
@Getter
@Setter
public class OrderNewCar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user1;

    @Column(name = "new_car_id")
    private Integer new_car_id;

    @Column(name = "status")
    private String status;

    @Column(name = "creation_date")
    private String creation_date;
}
