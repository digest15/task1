package com.edu.task1.consoleprog;

import com.edu.task1.dao.*;
import com.edu.task1.entity.*;

/**
 * Created by damager on 17.08.17.
 */
public class Main {
    public static void main(String[] args) {
        FactoryDao serializationApiFactoryDao = new SerializationApiFactoryDao();
        fill(serializationApiFactoryDao);
    }

    private static void fill(FactoryDao factoryDao) {
        //CarService
        GenericDao carServiceDao = factoryDao.getCarServiceDao();
        if (carServiceDao.getAll().size() == 0) {
            //1
            CarService carService = (CarService)carServiceDao.create();
            carService.setAddress("г.Иркутск, Александровское шоссе 11 км");
            carService.setName("Александровское");
            carService.setOpeningTime(10);
            carService.setClosingTime(20);
            carServiceDao.add(carService);

            //2
            carService = (CarService)carServiceDao.create();
            carService.setAddress("г.Иркутск, ул. Ширямова 10");
            carService.setName("На Ширямва");
            carService.setOpeningTime(9);
            carService.setClosingTime(19);
            carServiceDao.add(carService);

            //save
            carServiceDao.saveToFile();
        }

        //Color
        GenericDao colorDao = factoryDao.getColorDao();
        if (colorDao.getAll().size() == 0) {
            //1
            Color color = (Color)colorDao.create();
            color.setName("Красный");
            color.setRgb("255 0 0");

            //2
            color = (Color)colorDao.create();
            color.setName("Оранжевый");
            color.setRgb("255 128 0");

            //3
            color = (Color)colorDao.create();
            color.setName("Синий");
            color.setRgb("0 0 255");

            //4
            color = (Color)colorDao.create();
            color.setName("Фиолетовый");
            color.setRgb("128 0 255");

            //5
            color = (Color)colorDao.create();
            color.setName("Белый");
            color.setRgb("255 255 255");

            //6
            color = (Color)colorDao.create();
            color.setName("Черный");
            color.setRgb("0 0 0");

            //7
            color = (Color)colorDao.create();
            color.setName("Серый");
            color.setRgb("128 128 128");

            //8
            color = (Color)colorDao.create();
            color.setName("Зеленый");
            color.setRgb("0 255 0");

            //save
            colorDao.saveToFile();
        }

        //Mark
        GenericDao markDao = factoryDao.getMarkDao();
        if (markDao.getAll().size() == 0) {
            //1
            Mark mark = (Mark)markDao.create();
            mark.setManufacturer("Audi");

            //2
            mark = (Mark)markDao.create();
            mark.setManufacturer("BMW");

            //3
            mark = (Mark)markDao.create();
            mark.setManufacturer("Chevrolet");

            //4
            mark = (Mark)markDao.create();
            mark.setManufacturer("Ford");

            //5
            mark = (Mark)markDao.create();
            mark.setManufacturer("Honda");

            //6
            mark = (Mark)markDao.create();
            mark.setManufacturer("Hyundai");

            //7
            mark = (Mark)markDao.create();
            mark.setManufacturer("Lexus");

            //8
            mark = (Mark)markDao.create();
            mark.setManufacturer("Kia");

            //9
            mark = (Mark)markDao.create();
            mark.setManufacturer("Mazda");

            //10
            mark = (Mark)markDao.create();
            mark.setManufacturer("Mercedes-Benz");

            //11
            mark = (Mark)markDao.create();
            mark.setManufacturer("Mitsubishi");

            //12
            mark = (Mark)markDao.create();
            mark.setManufacturer("Nissan");

            //13
            mark = (Mark)markDao.create();
            mark.setManufacturer("Opel");

            //14
            mark = (Mark)markDao.create();
            mark.setManufacturer("Toyota");

            //15
            mark = (Mark)markDao.create();
            mark.setManufacturer("Лада");

            //16
            mark = (Mark)markDao.create();
            mark.setManufacturer("Скания");

            //17
            mark = (Mark)markDao.create();
            mark.setManufacturer("Камаз");

            //16
            mark = (Mark)markDao.create();
            mark.setManufacturer("Татра");

            //18
            mark = (Mark)markDao.create();
            mark.setManufacturer("Маз");

            //19
            mark = (Mark)markDao.create();
            mark.setManufacturer("Ман");

            //20
            mark = (Mark)markDao.create();
            mark.setManufacturer("Лиаз");

            //21
            mark = (Mark)markDao.create();
            mark.setManufacturer("ГАЗ");

            //save
            markDao.saveToFile();
        }

        //Car
        GenericDao carDao = factoryDao.getCarDao();
        if (carDao.getAll().size() == 0) {
            //1
            Car car = (Car)carDao.create();
            //car.
        }
    }
}
