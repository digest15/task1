package com.edu.task1.main;

import com.edu.task1.dao.CarServiceDao;
import com.edu.task1.dao.FactoryDao;
import com.edu.task1.dao.SerializationApiFactoryDao;
import com.edu.task1.entity.CarService;

/**
 * Created by damager on 17.08.17.
 */
public class Main {
    public static void main(String[] args) {
        FactoryDao factoryDao = new SerializationApiFactoryDao();
        CarServiceDao carServiceDao = factoryDao.getCarServiceDao();

        CarService carService = carServiceDao.create();
        carService.setAddress("г.Иркутск, Александровское шоссе 11 км");
        carService.setName("Александровское");
        carService.setOpeningTime(10);
        carService.setClosingTime(20);

        carServiceDao.add(carService);
        carServiceDao.writeToFile();
    }
}
