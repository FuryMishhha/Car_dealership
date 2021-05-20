package com.example.demo;


import com.example.demo.Models.New_car;
import com.example.demo.Models.Support_car;
import com.example.demo.Repositories.SupportCarRepository;
import com.example.demo.Services.SupportCarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SupportCarServiceTest {
    @InjectMocks
    private SupportCarService supportCarService;
    @Mock
    private SupportCarRepository supportCarRepository;
    @Captor
    private ArgumentCaptor<Support_car> captor;

    private Support_car support_car1, support_car2, support_car3;

    @BeforeEach
    void init() {
        support_car1 = new Support_car();
        support_car1.setId(1);
        support_car1.setBrand("brand1");
        support_car1.setModel("model1");
        support_car1.setMileage("mileage1");
        support_car1.setRelease_year("release_year1");
        support_car1.setBody("body1");
        support_car1.setColor("color1");
        support_car1.setEngine("engine1");
        support_car1.setDrive("drive1");
        support_car1.setWheel("wheel1");
        support_car1.setNumber_of_owners("number_of_owners1");
        support_car1.setPrice("price1");
        support_car1.setPicture("picture1");
        support_car1.setOrder_support_car_id(1);

        support_car2 = new Support_car();
        support_car2.setId(2);
        support_car2.setBrand("brand2");
        support_car2.setModel("model2");
        support_car2.setMileage("mileage2");
        support_car2.setRelease_year("release_year2");
        support_car2.setBody("body2");
        support_car2.setColor("color2");
        support_car2.setEngine("engine2");
        support_car2.setDrive("drive2");
        support_car2.setWheel("wheel2");
        support_car2.setNumber_of_owners("number_of_owners2");
        support_car2.setPrice("price2");
        support_car2.setPicture("picture2");
        support_car2.setOrder_support_car_id(2);

        support_car3 = new Support_car();
        support_car3.setId(3);
        support_car3.setBrand("brand3");
        support_car3.setModel("model3");
        support_car3.setMileage("mileage3");
        support_car3.setRelease_year("release_year3");
        support_car3.setBody("body3");
        support_car3.setColor("color3");
        support_car3.setEngine("engine3");
        support_car3.setDrive("drive3");
        support_car3.setWheel("wheel3");
        support_car3.setNumber_of_owners("number_of_owners3");
        support_car3.setPrice("price3");
        support_car3.setPicture("picture3");
        support_car3.setOrder_support_car_id(3);
    }

    @Test
    void addSupportCar() {
        supportCarService.addSupportCar(support_car2);
        Mockito.verify(supportCarRepository).save(captor.capture());
        Support_car captured = captor.getValue();
        assertEquals("brand2", captured.getBrand());
    }

    @Test
    void readAll() {
        ArrayList<Support_car> support_cars = new ArrayList<>();
        support_cars.add(support_car1);
        support_cars.add(support_car2);
        support_cars.add(support_car3);
        Mockito.when(supportCarRepository.findAll()).thenReturn(support_cars);
        assertEquals(support_cars, supportCarRepository.findAll());
    }

    @Test
    void findSupportCar() {
        Mockito.when(supportCarRepository.findSupport_carById(1)).thenReturn(support_car1);
        assertEquals(support_car1, supportCarRepository.findSupport_carById(1));
    }

    @Test
    void deleteNewCar() {
        supportCarService.deleteSupportCar(2);
        Mockito.verify(supportCarRepository).deleteSupport_carById(2);
    }
}
