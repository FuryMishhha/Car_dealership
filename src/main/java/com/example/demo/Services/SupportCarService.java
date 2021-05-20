package com.example.demo.Services;

import com.example.demo.Models.New_car;
import com.example.demo.Models.Support_car;
import com.example.demo.Repositories.NewCarRepository;
import com.example.demo.Repositories.SupportCarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@Transactional
public class SupportCarService {
    @Autowired
    private SupportCarRepository supportCarRepository;

    public SupportCarService(SupportCarRepository supportCarRepository) {
        this.supportCarRepository = supportCarRepository;
    }

    public void addSupportCar(Support_car support_car){
        log.info("Save support car " + support_car.getBrand() + support_car.getModel());
        supportCarRepository.save(support_car);
    }

    public List<Support_car> readAll() {
        log.info("Find all support cars");
        List <Support_car> support_carList = supportCarRepository.findAll();
        support_carList.sort(Comparator.comparingInt(Support_car::getId));
        return support_carList;
    }

    public Support_car findSupportCar(Integer id){
        log.info("Find support car, whose Id = {}",id);
        return supportCarRepository.findById(id).orElse(null);
    }

    public void deleteSupportCar(Integer id){
        log.info("Delete support car, whose Id = {}", id);
        supportCarRepository.deleteSupport_carById(id);
    }
}