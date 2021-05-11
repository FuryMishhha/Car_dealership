package com.example.demo.Services;

import com.example.demo.Models.New_car;
import com.example.demo.Repositories.NewCarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@Transactional
public class NewCarService {
    @Autowired
    private NewCarRepository newCarRepository;

    public NewCarService(NewCarRepository newCarRepository) {
        this.newCarRepository = newCarRepository;
    }

    public void addNewCar(New_car new_car){
        log.info("Save new car " + new_car.getBrand() + " " + new_car.getModel());
        newCarRepository.save(new_car);
    }

    public List<New_car> readAll() {
        log.info("Find all new cars");
        List <New_car> new_carList = newCarRepository.findAll();
        new_carList.sort(Comparator.comparingInt(New_car::getId));
        return new_carList;
    }

    public New_car findNewCar(Integer id){
        log.info("Find new car, whose Id = {}",id);
        return newCarRepository.findById(id).orElse(null);
    }

    public void deleteNewCar(Integer id){
        log.info("Delete new car, whose Id = {}", id);
        newCarRepository.deleteNew_carById(id);
    }
}