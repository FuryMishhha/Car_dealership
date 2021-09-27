package com.example.demo.Services;

import com.example.demo.Models.New_car;
import com.example.demo.Models.Support_car;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
/**
 * Класс CriteriaService, который отвечает за бизнес-логику работы сортировки.
 * Имеет два приватных свойства - sessionFactory и session
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CriteriaService {

    /** Параметр сессии */
    private final SessionFactory sessionFactory;

    /** Параметр сессии */
    private Session session;

    /**
     * Параметр, неоходимый для сортировки
     */
    @Autowired
    private EntityManager entityManager;

    /** Постконструктор */
    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }

    /** Предеструктор */
    @PreDestroy
    void closeSession() {
        session.close();
    }

    /**
     * Метод фильтрации новых авто по модели и типу кузова
     * @param model модель авто
     * @param body тип кузова авто
     * @return отфильтрованный список новых авто по модели и типу кузова
     */
    public List<New_car> takeByWithoutBrand(String model, String body) {
        log.info("Find new cars whose && model && body are " + model + " " + body);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<New_car> criteriaQuery = criteriaBuilder.createQuery(New_car.class);
        Root<New_car> root = criteriaQuery.from(New_car.class);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);
        Predicate predicateFinal = criteriaBuilder.and(predicateForModel,predicateForBody);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации новых авто по бренду и типу кузова
     * @param brand бренд авто
     * @param body тип кузова авто
     * @return отфильтрованный список новых авто по бренду и типу кузова
     */
    public List<New_car> takeByWithoutModel(String brand, String body) {
        log.info("Find new cars whose brand && body are " + brand +  " " + body);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<New_car> criteriaQuery = criteriaBuilder.createQuery(New_car.class);
        Root<New_car> root = criteriaQuery.from(New_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand,predicateForBody);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации новых авто по бренду и модели
     * @param brand бренд авто
     * @param model модель авто
     * @return отфильтрованный список новых авто по бренду и модели
     */
    public List<New_car> takeByWithoutBody(String brand, String model) {
        log.info("Find new cars whose brand && model are " + brand + " " + model);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<New_car> criteriaQuery = criteriaBuilder.createQuery(New_car.class);
        Root<New_car> root = criteriaQuery.from(New_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand,predicateForModel);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации новых авто по бренду
     * @param brand бренд авто
     * @return отфильтрованный список новых авто по бренду
     */
    public List<New_car> takeByBrand(String brand) {
        log.info("Find new cars whose brand is " + brand);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<New_car> criteriaQuery = criteriaBuilder.createQuery(New_car.class);
        Root<New_car> root = criteriaQuery.from(New_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации новых авто по модели
     * @param model модель авто
     * @return отфильтрованный список новых авто по модели
     */
    public List<New_car> takeByModel(String model) {
        log.info("Find new cars whose model is " + model);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<New_car> criteriaQuery = criteriaBuilder.createQuery(New_car.class);
        Root<New_car> root = criteriaQuery.from(New_car.class);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateFinal = criteriaBuilder.and(predicateForModel);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации новых авто по типу кузова
     * @param body тип кузова авто
     * @return отфильтрованный список новых авто по типу кузова
     */
    public List<New_car> takeByBody(String body) {
        log.info("Find new cars whose body is " + body);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<New_car> criteriaQuery = criteriaBuilder.createQuery(New_car.class);
        Root<New_car> root = criteriaQuery.from(New_car.class);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBody);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации новых авто по бренду, модели и типу кузова
     * @param brand бренд авто
     * @param model модель авто
     * @param body тип кузова авто
     * @return отфильтрованный список новых авто по бренду, модели и типу кузова
     */
    public List<New_car> takeByAllNew(String brand, String model, String body) {
        log.info("Find new cars whose brand && model && body are " + brand + " " + model + " " + body);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<New_car> criteriaQuery = criteriaBuilder.createQuery(New_car.class);
        Root<New_car> root = criteriaQuery.from(New_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand,predicateForModel,predicateForBody);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по модели, типу кузова и году выпуска
     * @param model модель авто
     * @param body тип кузова авто
     * @param year год выпуска авто
     * @return отфильтрованный список подержанных авто по модели, типу кузова и году выпуска
     */
    public List<Support_car> takeByWithoutBrandSup(String model, String body, String year) {
        log.info("Find support cars whose && model && body && year are " + model + " " + body + " " + year);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);
        Predicate predicateForYear = criteriaBuilder.like(root.get("release_year"), year);
        Predicate predicateFinal = criteriaBuilder.and(predicateForModel,predicateForBody,predicateForYear);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по бренду, типу кузова и году выпуска
     * @param brand бренд авто
     * @param body тип кузова авто
     * @param year год выпуска авто
     * @return отфильтрованный список подержанных авто по бренду, типу кузова и году выпуска
     */
    public List<Support_car> takeByWithoutModelSup(String brand, String body, String year) {
        log.info("Find new cars whose brand && body && year are " + brand + " " + body + " " + year);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);
        Predicate predicateForYear = criteriaBuilder.like(root.get("release_year"), year);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand,predicateForBody,predicateForYear);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по бренду, модели и году выпуска
     * @param brand бренд авто
     * @param model модель авто
     * @param year год выпуска авто
     * @return тфильтрованный список подержанных авто по бренду, модели и году выпуска
     */
    public List<Support_car> takeByWithoutBodySup(String brand, String model, String year) {
        log.info("Find new cars whose brand && model && year are " + brand + " " + model + " " + year);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateForYear = criteriaBuilder.like(root.get("release_year"), year);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand,predicateForModel,predicateForYear);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по бренду, модели и типу кузова
     * @param brand бренд авто
     * @param model модель авто
     * @param body тип кузова авто
     * @return отфильтрованный список подержанных авто по бренду, модели и типу кузова
     */
    public List<Support_car> takeByWithoutYearSup(String brand, String model, String body) {
        log.info("Find new cars whose brand && model && body are " + brand + " " + model + " " + body);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand,predicateForModel,predicateForBody);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по типу кузова и году выпуска
     * @param body тип кузова авто
     * @param year год выпуска авто
     * @return отфильтрованный список подержанных авто по типу кузова и году выпуска
     */
    public List<Support_car> takeByWithoutBrandModel(String body, String year) {
        log.info("Find new cars whose body && year are " + body + " " + year);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);
        Predicate predicateForYear = criteriaBuilder.like(root.get("release_year"), year);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBody,predicateForYear);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по модели и году выпуска
     * @param model модель авто
     * @param year год выпуска авто
     * @return отфильтрованный список подержанных авто по модели и году выпуска
     */
    public List<Support_car> takeByWithoutBrandBody(String model, String year) {
        log.info("Find new cars whose && model && year are " + model + " " + year);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateForYear = criteriaBuilder.like(root.get("release_year"), year);
        Predicate predicateFinal = criteriaBuilder.and(predicateForModel,predicateForYear);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по модели и типу кузова
     * @param model модель авто
     * @param body тип кузова авто
     * @return отфильтрованный список подержанных авто по модели и типу кузова
     */
    public List<Support_car> takeByWithoutBrandYear(String model, String body) {
        log.info("Find new cars whose model && body are " + model + " " + body );
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);;
        Predicate predicateFinal = criteriaBuilder.and(predicateForModel,predicateForBody);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по бренду и году выпуска
     * @param brand бренд авто
     * @param year год выпуска авто
     * @return отфильтрованный список подержанных авто по бренду и году выпуска
     */
    public List<Support_car> takeByWithoutModelBody(String brand, String year) {
        log.info("Find new cars whose brand && year are " + brand + " " + year);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateForYear = criteriaBuilder.like(root.get("release_year"), year);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand,predicateForYear);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по бренду и типу кузова
     * @param brand бренд авто
     * @param body тип кузова авто
     * @return отфильтрованный список подержанных авто по бренду и типу кузова
     */
    public List<Support_car> takeByWithoutModelYear(String brand, String body) {
        log.info("Find new cars whose brand && body  are " + brand + " " + body);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand,predicateForBody);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по бренду и модели
     * @param brand бренд авто
     * @param model модель авто
     * @return отфильтрованный список подержанных авто по бренду и модели
     */
    public List<Support_car> takeByWithoutBodyYear(String brand, String model) {
        log.info("Find new cars whose brand && model are " + brand + " " + model);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand,predicateForModel);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по бренду
     * @param brand бренд авто
     * @return отфильтрованный список подержанных авто по бренду
     */
    public List<Support_car> takeByBrandSup(String brand) {
        log.info("Find new cars whose brand is " + brand);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по модели
     * @param model модель авто
     * @return отфильтрованный список подержанных авто по модели
     */
    public List<Support_car> takeByModelSup(String model) {
        log.info("Find new cars whose model is " + model);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateFinal = criteriaBuilder.and(predicateForModel);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по типу кузова
     * @param body тип кузова авто
     * @return отфильтрованный список подержанных авто по типу кузова
     */
    public List<Support_car>takeByBodySup(String body) {
        log.info("Find new cars whose body is " + body);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBody);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по году выпуска
     * @param year год выпуска авто
     * @return отфильтрованный список подержанных авто по году выпуска
     */
    public List<Support_car> takeByYear(String year) {
        log.info("Find new cars whose year is " + year);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForYear = criteriaBuilder.like(root.get("release_year"), year);
        Predicate predicateFinal = criteriaBuilder.and(predicateForYear);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Метод фильтрации подержанных авто по бренду, модели, типу кузова и году выпуска
     * @param brand бренд авто
     * @param model модель авто
     * @param body тип кузова авто
     * @param year год выпуска авто
     * @return отфильтрованный список подержанных авто по бренду, модели, типу кузова и году выпуска
     */
    public List<Support_car> takeByAllSup(String brand, String model, String body, String year) {
        log.info("Find new cars whose brand && model && body && year are " + brand + " " + model + " " + body + " " + year);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support_car> criteriaQuery = criteriaBuilder.createQuery(Support_car.class);
        Root<Support_car> root = criteriaQuery.from(Support_car.class);
        Predicate predicateForBrand = criteriaBuilder.like(root.get("brand"), brand);
        Predicate predicateForModel = criteriaBuilder.like(root.get("model"), model);
        Predicate predicateForBody = criteriaBuilder.like(root.get("body"), body);
        Predicate predicateForYear = criteriaBuilder.like(root.get("release_year"), year);
        Predicate predicateFinal = criteriaBuilder.and(predicateForBrand,predicateForModel,predicateForBody,predicateForYear);
        criteriaQuery.where(predicateFinal);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}