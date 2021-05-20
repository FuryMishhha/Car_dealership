package com.example.demo;

import com.example.demo.Models.New_car;
import com.example.demo.Repositories.NewCarRepository;
import com.example.demo.Services.NewCarService;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NewCarServiceTest {
    @InjectMocks
    private NewCarService newCarService;
    @Mock
    private NewCarRepository newCarRepository;
    @Captor
    private ArgumentCaptor<New_car> captor;

    private New_car new_car1, new_car2, new_car3;
    @BeforeEach
    void init() {
        new_car1 = new New_car();
        new_car1.setId(1);
        new_car1.setBrand("brand1");
        new_car1.setModel("model1");
        new_car1.setRelease_year("release_year1");
        new_car1.setBody("body1");
        new_car1.setColor("color1");
        new_car1.setEngine("engine1");
        new_car1.setDrive("drive1");
        new_car1.setWheel("wheel1");
        new_car1.setPrice("price1");
        new_car1.setPicture("picture1");
        new_car1.setOrder_new_car_id(1);

        new_car2 = new New_car();
        new_car2.setId(2);
        new_car2.setBrand("brand2");
        new_car2.setModel("model2");
        new_car2.setRelease_year("release_year2");
        new_car2.setBody("body2");
        new_car2.setColor("color2");
        new_car2.setEngine("engine2");
        new_car2.setDrive("drive2");
        new_car2.setWheel("wheel2");
        new_car2.setPrice("price2");
        new_car2.setPicture("picture2");
        new_car2.setOrder_new_car_id(2);

        new_car3 = new New_car();
        new_car3.setId(3);
        new_car3.setBrand("brand3");
        new_car3.setModel("model3");
        new_car3.setRelease_year("release_year3");
        new_car3.setBody("body3");
        new_car3.setColor("color3");
        new_car3.setEngine("engine3");
        new_car3.setDrive("drive3");
        new_car3.setWheel("wheel3");
        new_car3.setPrice("price3");
        new_car3.setPicture("picture3");
        new_car3.setOrder_new_car_id(3);
    }

    @Test
    void addNewCar() {
        newCarService.addNewCar(new_car2);
        Mockito.verify(newCarRepository).save(captor.capture());
        New_car captured = captor.getValue();
        assertEquals("brand2", captured.getBrand());
    }

    @Test
    void findNewCar() {
        Mockito.when(newCarRepository.findNew_carById(1)).thenReturn(new_car1);
        assertEquals(new_car1, newCarRepository.findNew_carById(1));
    }

    @Test
    void readAll() {
        ArrayList<New_car> new_car = new ArrayList<>();
        new_car.add(new_car1);
        new_car.add(new_car2);
        new_car.add(new_car3);
        Mockito.when(newCarRepository.findAll()).thenReturn(new_car);
        assertEquals(new_car, newCarRepository.findAll());
    }

    @Test
    void deleteNewCar() {
        newCarService.deleteNewCar(2);
        Mockito.verify(newCarRepository).deleteNew_carById(2);
    }
}
