package com.edu.task1.main;

import com.edu.task1.dao.*;
import com.edu.task1.entity.*;

/**
 * Created by damager on 17.08.17.
 */
public class Main {
    public static void main(String[] args) {
        FactoryDao factoryDao = new SerializationApiFactoryDao();

        //CarService
        CarServiceDao carServiceDao = factoryDao.getCarServiceDao();
        if (carServiceDao.getAll().size() == 0) {
            //1
            CarService carService = carServiceDao.create();
            carService.setAddress("г.Иркутск, Александровское шоссе 11 км");
            carService.setName("Александровское");
            carService.setOpeningTime(10);
            carService.setClosingTime(20);
            carServiceDao.add(carService);

            //2
            carService = carServiceDao.create();
            carService.setAddress("г.Иркутск, ул. Ширямова 10");
            carService.setName("На Ширямва");
            carService.setOpeningTime(9);
            carService.setClosingTime(19);
            carServiceDao.add(carService);

            //save
            carServiceDao.saveToFile();
        }

        //Color
        ColorDao colorDao = factoryDao.getColorDao();
        if (colorDao.getAll().size() == 0) {
            //1
            Color color = colorDao.create();
            color.setName("Красный");
            color.setRgb("255 0 0");

            //2
            color = colorDao.create();
            color.setName("Оранжевый");
            color.setRgb("255 128 0");

            //3
            color = colorDao.create();
            color.setName("Синий");
            color.setRgb("0 0 255");

            //4
            color = colorDao.create();
            color.setName("Фиолетовый");
            color.setRgb("128 0 255");

            //5
            color = colorDao.create();
            color.setName("Белый");
            color.setRgb("255 255 255");

            //6
            color = colorDao.create();
            color.setName("Черный");
            color.setRgb("0 0 0");

            //7
            color = colorDao.create();
            color.setName("Серый");
            color.setRgb("128 128 128");

            //8
            color = colorDao.create();
            color.setName("Зеленый");
            color.setRgb("0 255 0");

            //save
            colorDao.saveToFile();
        }

        //Mark
        MarkDao markDao = factoryDao.getMarkDao();
        if (markDao.getAll().size() == 0) {
            //1
            Mark mark = markDao.create();
            mark.setManufacturer("Audi");

            //2
            mark = markDao.create();
            mark.setManufacturer("BMW");

            //3
            mark = markDao.create();
            mark.setManufacturer("Chevrolet");

            //4
            mark = markDao.create();
            mark.setManufacturer("Ford");

            //5
            mark = markDao.create();
            mark.setManufacturer("Honda");

            //6
            mark = markDao.create();
            mark.setManufacturer("Hyundai");

            //7
            mark = markDao.create();
            mark.setManufacturer("Lexus");

            //8
            mark = markDao.create();
            mark.setManufacturer("Kia");

            //9
            mark = markDao.create();
            mark.setManufacturer("Mazda");

            //10
            mark = markDao.create();
            mark.setManufacturer("Mercedes-Benz");

            //11
            mark = markDao.create();
            mark.setManufacturer("Mitsubishi");

            //12
            mark = markDao.create();
            mark.setManufacturer("Nissan");

            //13
            mark = markDao.create();
            mark.setManufacturer("Opel");

            //14
            mark = markDao.create();
            mark.setManufacturer("Toyota");

            //15
            mark = markDao.create();
            mark.setManufacturer("Лада");

            //save
            markDao.saveToFile();
        }

        //Car
        CarDao carDao = factoryDao.getCarDao();
        if (carDao.getAll().size() == 0) {
            //1
            Car car = carDao.create();
            //car.
        }
    }
}
