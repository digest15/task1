package com.edu.task1.consoleprog;

import com.edu.task1.helpers.DateHelper;
import com.edu.task1.reports.Reports;
import com.edu.task1.threads.*;
import com.edu.task1.dao.*;
import com.edu.task1.entity.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by damager on 17.08.17.
 */
public class Main {
    public static void main(String[] args) {
//        FactoryDao factory = new SerializationApiFactoryDao();
//        fill(factory);

//        FactoryDao factory = new CsvFactoryDao();
//        fill(factory);

//        FactoryDao factory = new MyTxtFormatSerializationFactoryDao();
//        fill(factory);

//        FactoryDao factory = new GsonFactoryDao();
//        fill(factory);

        FactoryDao factory = new XstreamFactoryDao();
        fill(factory);

        Reports.allReportsByRepiarToConsole(factory.getRepairDao(), factory.getCarServiceDao());

    }

    private static void fill(FactoryDao factoryDao) {
        try {
            CarServiceThread carServiceThread = new CarServiceThread(factoryDao);
            ColorThread colorThread = new ColorThread(factoryDao);
            MarkThread markThread = new MarkThread(factoryDao);
            MechanicTread mechanicTread = new MechanicTread(factoryDao);

            colorThread.getThread().join();
            markThread.getThread().join();

            GenericDao colorDao = colorThread.getDao();
            CarThread carThread = new CarThread(factoryDao, colorDao, markThread.getDao());
            BusThread busThread = new BusThread(factoryDao, colorDao, markThread.getDao());
            TruckThread truckThread = new TruckThread(factoryDao, colorDao, markThread.getDao());

            carServiceThread.getThread().join();
            mechanicTread.getThread().join();
            carThread.getThread().join();
            busThread.getThread().join();
            truckThread.getThread().join();

            List<Machine> machineList = new ArrayList<>();
            machineList.addAll(carThread.getDao().getAll());
            machineList.addAll(busThread.getDao().getAll());
            machineList.addAll(truckThread.getDao().getAll());
            RepairThread repairThread = new RepairThread(factoryDao, carServiceThread.getDao(), mechanicTread.getDao(), machineList);
        } catch (InterruptedException e) {
            System.out.println("Ошибка потока " + e);
        }
    }

    private static void fill1(FactoryDao factoryDao) {
        Random random = new Random(new Date().getTime());

        //CarService
        GenericDao carServiceDao = factoryDao.getCarServiceDao();
        if (carServiceDao.getCount() == 0) {
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
        if (colorDao.getCount() == 0) {
            //1
            Color color = (Color)colorDao.create();
            color.setName("Красный");
            color.setRgb("255 0 0");
            colorDao.add(color);

            //2
            color = (Color)colorDao.create();
            color.setName("Оранжевый");
            color.setRgb("255 128 0");
            colorDao.add(color);

            //3
            color = (Color)colorDao.create();
            color.setName("Синий");
            color.setRgb("0 0 255");
            colorDao.add(color);

            //4
            color = (Color)colorDao.create();
            color.setName("Фиолетовый");
            color.setRgb("128 0 255");
            colorDao.add(color);

            //5
            color = (Color)colorDao.create();
            color.setName("Белый");
            color.setRgb("255 255 255");
            colorDao.add(color);

            //6
            color = (Color)colorDao.create();
            color.setName("Черный");
            color.setRgb("0 0 0");
            colorDao.add(color);

            //7
            color = (Color)colorDao.create();
            color.setName("Серый");
            color.setRgb("128 128 128");
            colorDao.add(color);

            //8
            color = (Color)colorDao.create();
            color.setName("Зеленый");
            color.setRgb("0 255 0");
            colorDao.add(color);

            //save
            colorDao.saveToFile();
        }

        //Mark
        GenericDao markDao = factoryDao.getMarkDao();
        if (markDao.getCount() == 0) {
            //1
            Mark mark = (Mark)markDao.create();
            mark.setManufacturer("Audi");
            markDao.add(mark);

            //2
            mark = (Mark)markDao.create();
            mark.setManufacturer("BMW");
            markDao.add(mark);

            //3
            mark = (Mark)markDao.create();
            mark.setManufacturer("Chevrolet");
            markDao.add(mark);

            //4
            mark = (Mark)markDao.create();
            mark.setManufacturer("Ford");
            markDao.add(mark);

            //5
            mark = (Mark)markDao.create();
            mark.setManufacturer("Honda");
            markDao.add(mark);

            //6
            mark = (Mark)markDao.create();
            mark.setManufacturer("Hyundai");
            markDao.add(mark);

            //7
            mark = (Mark)markDao.create();
            mark.setManufacturer("Lexus");
            markDao.add(mark);

            //8
            mark = (Mark)markDao.create();
            mark.setManufacturer("Kia");
            markDao.add(mark);

            //9
            mark = (Mark)markDao.create();
            mark.setManufacturer("Mazda");
            markDao.add(mark);

            //10
            mark = (Mark)markDao.create();
            mark.setManufacturer("Mercedes-Benz");
            markDao.add(mark);

            //11
            mark = (Mark)markDao.create();
            mark.setManufacturer("Mitsubishi");
            markDao.add(mark);

            //12
            mark = (Mark)markDao.create();
            mark.setManufacturer("Nissan");
            markDao.add(mark);

            //13
            mark = (Mark)markDao.create();
            mark.setManufacturer("Opel");
            markDao.add(mark);

            //14
            mark = (Mark)markDao.create();
            mark.setManufacturer("Toyota");
            markDao.add(mark);

            //15
            mark = (Mark)markDao.create();
            mark.setManufacturer("Лада");
            markDao.add(mark);

            //16
            mark = (Mark)markDao.create();
            mark.setManufacturer("Скания");
            markDao.add(mark);

            //17
            mark = (Mark)markDao.create();
            mark.setManufacturer("Камаз");
            markDao.add(mark);

            //18
            mark = (Mark)markDao.create();
            mark.setManufacturer("Татра");
            markDao.add(mark);

            //19
            mark = (Mark)markDao.create();
            mark.setManufacturer("Маз");
            markDao.add(mark);

            //20
            mark = (Mark)markDao.create();
            mark.setManufacturer("Ман");
            markDao.add(mark);

            //21
            mark = (Mark)markDao.create();
            mark.setManufacturer("Лиаз");
            markDao.add(mark);

            //22
            mark = (Mark)markDao.create();
            mark.setManufacturer("ГАЗ");
            markDao.add(mark);

            //save
            markDao.saveToFile();
        }

        //Car
        GenericDao carDao = factoryDao.getCarDao();
        if (carDao.getCount() == 0) {
            Car car;
            for (int i=0;i <=3;i++) {
                car = (Car) carDao.create();
                car.setNamePicking("Люкс");
                car.setColor((Color)colorDao.getByIndex(random.nextInt(colorDao.getCount())));
                car.setNumberPassengerSeats(5);
                car.setMark((Mark)markDao.getByIndex(random.nextInt(markDao.getCount() - 7))); //Только легковые марки
                car.setReleaseYear(new Date());
                car.setVin(String.valueOf(Math.abs(random.nextLong())));
               //ThreadLocalRandom.current().nextInt();

//                car.colors = colorDao.getAll();
//
//                String[] strings = {"1", "2", "3", "4", "5"};
//                car.strings = Arrays.asList(strings);
//
//                int[] ints = {1, 2, 3, 4, 5};
//                car.ints = ints;

                carDao.add(car);
            }
            carDao.saveToFile();
        }

        //Bus
        GenericDao busDao = factoryDao.getBusDao();
        if (busDao.getCount() == 0) {
            Bus bus;
            for (int i=0;i <=10;i++) {
                bus = (Bus) busDao.create();
                bus.setNamePicking("Люкс");
                bus.setColor((Color)colorDao.getByIndex(random.nextInt(colorDao.getCount())));
                bus.setNumberPassengerSeats(random.nextInt(20) + 20);
                bus.setNumberPassengerStanding(random.nextInt(20) + 20);
                bus.setMark((Mark)markDao.getByIndex(random.nextInt(7)+15)); //Только грузовые и автобусы
                bus.setReleaseYear(new Date());
                bus.setVin(String.valueOf(Math.abs(random.nextLong())));
                //ThreadLocalRandom.current().nextInt();
                busDao.add(bus);
            }
            busDao.saveToFile();
        }

        //Truck
        GenericDao truckDao = factoryDao.getTruckDao();
        if (truckDao.getCount() == 0) {
            Truck truck;
            for (int i=0;i <=10;i++) {
                truck = (Truck) truckDao.create();
                truck.setNamePicking("Люкс");
                truck.setNumberAxle(random.nextInt(6) + 2);
                truck.setColor((Color)colorDao.getByIndex(random.nextInt(colorDao.getCount())));
                truck.setMark((Mark)markDao.getByIndex(random.nextInt(7)+15)); //Только грузовые и автобусы
                truck.setReleaseYear(new Date());
                truck.setVin(String.valueOf(Math.abs(random.nextLong())));
                //ThreadLocalRandom.current().nextInt();
                truckDao.add(truck);
            }
            truckDao.saveToFile();
        }

        //Mechanic
        GenericDao mechanicDao = factoryDao.getMehanicDao();
        if (mechanicDao.getCount() == 0) {
            //1
            Mechanic mechanic = (Mechanic)mechanicDao.create();
            mechanic.setName("Иван");
            mechanic.setLastName("Иванов");
            mechanic.setSalary(new BigDecimal(10000));
            mechanicDao.add(mechanic);

            //2
            mechanic = (Mechanic)mechanicDao.create();
            mechanic.setName("Петя");
            mechanic.setLastName("Петров");
            mechanic.setSalary(new BigDecimal(20000));
            mechanicDao.add(mechanic);

            //3
            mechanic = (Mechanic)mechanicDao.create();
            mechanic.setName("Саша");
            mechanic.setLastName("Сидоров");
            mechanic.setSalary(new BigDecimal(30000));
            mechanicDao.add(mechanic);

            //4
            mechanic = (Mechanic)mechanicDao.create();
            mechanic.setName("Дарма");
            mechanic.setLastName("Бельдыев");
            mechanic.setSalary(new BigDecimal(20000));
            mechanicDao.add(mechanic);

            //5
            mechanic = (Mechanic)mechanicDao.create();
            mechanic.setName("Вася");
            mechanic.setLastName("Пупкин");
            mechanic.setSalary(new BigDecimal(5000));
            mechanicDao.add(mechanic);

            //6
            mechanic = (Mechanic)mechanicDao.create();
            mechanic.setName("Миша");
            mechanic.setLastName("Малкин");
            mechanic.setSalary(new BigDecimal(30000));
            mechanicDao.add(mechanic);

            //7
            mechanic = (Mechanic)mechanicDao.create();
            mechanic.setName("Миша");
            mechanic.setLastName("Палкин");
            mechanic.setSalary(new BigDecimal(40000));
            mechanicDao.add(mechanic);

            //8
            mechanic = (Mechanic)mechanicDao.create();
            mechanic.setName("Федор");
            mechanic.setLastName("Федоров");
            mechanic.setSalary(new BigDecimal(50000));
            mechanicDao.add(mechanic);

            //9
            mechanic = (Mechanic)mechanicDao.create();
            mechanic.setName("Степан");
            mechanic.setLastName("Степанов");
            mechanic.setSalary(new BigDecimal(25000));
            mechanicDao.add(mechanic);

            //10
            mechanic = (Mechanic)mechanicDao.create();
            mechanic.setName("Кеша");
            mechanic.setLastName("Кротов");
            mechanic.setSalary(new BigDecimal(35000));
            mechanicDao.add(mechanic);

            //save
            mechanicDao.saveToFile();
        }


        ArrayList<Machine> machineList = new ArrayList<>();
        machineList.addAll(carDao.getAll());
//        machineList.addAll(busDao.getAll());
//        machineList.addAll(truckDao.getAll());

        GenericDao repairDao = factoryDao.getRepairDao();
        if (repairDao.getCount() == 0) {
            Repair repair;
            for (int i=0; i < 100; i++) {
                repair = (Repair) repairDao.create();
                repair.setDateTime(DateHelper.randomDate());
                repair.setCarServise((CarService)carServiceDao.getByIndex(random.nextInt(2)));
                repair.setMechanic((Mechanic)mechanicDao.getByIndex(random.nextInt(mechanicDao.getCount())));
                repair.setMachine((Machine) machineList.get(random.nextInt(machineList.size())));
                repair.setAmount(new BigDecimal(random.nextDouble() * random.nextInt(10000)));
                repairDao.add(repair);
            }
            //repairDao.saveToFile();
        }

        Reports.allReportsByRepiarToConsole(repairDao, carServiceDao);
    }
}
