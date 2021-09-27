package com.example.demo.Services;

import com.example.demo.Models.New_car;
import com.example.demo.Repositories.NewCarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
/**
 * Класс NewCarService, который отвечает за бизнес-логику работы с новыми авто.
 * Имеет приватное свойство newCarRepository
 */
@Slf4j
@Service
@Transactional
public class NewCarService {

    /**
     * Репозиторий, необходимый для работы с базой новых авто
     */
    @Autowired
    private NewCarRepository newCarRepository;

    /**
     * Конструктор класса NewCarService
     * @param newCarRepository репозиторий, необходимый для работы с базой новых авто
     */
    public NewCarService(NewCarRepository newCarRepository) {
        this.newCarRepository = newCarRepository;
    }

    /**
     * Метод добавления нового авто
     * @param new_car новый авто
     */
    public void addNewCar(New_car new_car){
        log.info("Save new car " + new_car.getBrand() + " " + new_car.getModel());
        newCarRepository.save(new_car);
    }

    /**
     * Метод вывода всех новых авто
     * @return - список новых авто
     */
    public List<New_car> readAll() {
        log.info("Find all new cars");
        List <New_car> new_carList = newCarRepository.findAll();
        new_carList.sort(Comparator.comparingInt(New_car::getId));
        return new_carList;
    }

    /**
     * Метод поиска нового авто
     * @param id номер нового подержанного авто
     * @return новый автомобиль
     */
    public New_car findNewCar(Integer id){
        log.info("Find new car, whose Id = {}",id);
        return newCarRepository.findById(id).orElse(null);
    }

    /**
     * Метод удаления нового авто
     * @param id номер нового авто
     */
    public void deleteNewCar(Integer id){
        log.info("Delete new car, whose Id = {}", id);
        newCarRepository.deleteNew_carById(id);
    }
}