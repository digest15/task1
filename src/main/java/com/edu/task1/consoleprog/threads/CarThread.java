package com.edu.task1.consoleprog.threads;

import com.edu.task1.dao.FactoryDao;
import com.edu.task1.dao.GenericDao;
import com.edu.task1.entity.Car;
import com.edu.task1.entity.Color;
import com.edu.task1.entity.Mark;
import java.util.Random;
import java.util.Date;

public class CarThread extends AbstractEntityThread {
    private GenericDao colorDao;
    private GenericDao markDao;
    private Random random;

    public CarThread(FactoryDao factoryDao, GenericDao colorDao, GenericDao markDao) {
        super(factoryDao, "CarThread");
        this.colorDao = colorDao;
        this.markDao = markDao;
        this.random = new Random(new Date().getTime());
    }

    @Override
    protected GenericDao createDao() {
        return this.factoryDao.getCarDao();
    }

    @Override
    public void run() {
        if (this.dao.getCount() == 0) {
                Car car;
            for (int i=0;i <=1000;i++) {
                car = (Car) this.dao.create();
                car.setNamePicking("Люкс");
                car.setColor((Color)this.colorDao.getByIndex(random.nextInt(this.colorDao.getCount())));
                car.setNumberPassengerSeats(5);
                car.setMark((Mark)this.markDao.getByIndex(random.nextInt(this.markDao.getCount() - 7))); //Только легковые марки
                car.setReleaseYear(new Date());
                car.setVin(String.valueOf(random.nextLong()));
                //ThreadLocalRandom.current().nextInt();
                this.dao.add(car);
            }
            this.dao.saveToFile();
        }
    }
}
