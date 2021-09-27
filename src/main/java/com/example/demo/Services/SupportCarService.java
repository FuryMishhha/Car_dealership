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
/**
 * Класс SupportCarService, который отвечает за бизнес-логику работы с подерджанными авто.
 * Имеет приватное свойство supportCarRepository
 */
@Slf4j
@Service
@Transactional
public class SupportCarService {

    /**
     * Репозиторий, необходимый для работы с базой подержанных авто
     */
    @Autowired
    private SupportCarRepository supportCarRepository;

    /**
     * Конструктор класса SupportCarService
     * @param supportCarRepository репозиторий, необходимый для работы с базой подержанных авто
     */
    public SupportCarService(SupportCarRepository supportCarRepository) {
        this.supportCarRepository = supportCarRepository;
    }

    /**
     * Метод добавления подержанного авто
     * @param support_car подержанный авто
     */
    public void addSupportCar(Support_car support_car){
        log.info("Save support car " + support_car.getBrand() + support_car.getModel());
        supportCarRepository.save(support_car);
    }

    /**
     * Метод вывода всех подержанных авто
     * @return - список подержанных авто
     */
    public List<Support_car> readAll() {
        log.info("Find all support cars");
        List <Support_car> support_carList = supportCarRepository.findAll();
        support_carList.sort(Comparator.comparingInt(Support_car::getId));
        return support_carList;
    }

    /**
     * Метод поиска подержанного авто
     * @param id номер подержанного авто
     * @return подержанный автомобиль
     */
    public Support_car findSupportCar(Integer id){
        log.info("Find support car, whose Id = {}",id);
        return supportCarRepository.findById(id).orElse(null);
    }

    /**
     * Метод удаления подержанного авто
     * @param id номер подержанного авто
     */
    public void deleteSupportCar(Integer id){
        log.info("Delete support car, whose Id = {}", id);
        supportCarRepository.deleteSupport_carById(id);
    }
}