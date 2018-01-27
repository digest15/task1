package com.edu.task1.threads;

import com.edu.task1.dao.FactoryDao;
import com.edu.task1.dao.GenericDao;
import com.edu.task1.entity.Bus;
import com.edu.task1.entity.Color;
import com.edu.task1.entity.Mark;

import java.util.Date;
import java.util.Random;

public class BusThread extends AbstractEntityThread {
    private GenericDao colorDao;
    private GenericDao markDao;
    private Random random;

    public BusThread(FactoryDao factoryDao, GenericDao colorDao, GenericDao markDao) {
        super(factoryDao, "BusThread");
        this.colorDao = colorDao;
        this.markDao = markDao;
        this.random = new Random(new Date().getTime());
    }

    @Override
    protected GenericDao createDao() {
        return factoryDao.getBusDao();
    }

    @Override
    public void run() {
        if (dao.getCount() == 0) {
            for (int i=0;i <=1000;i++) {
                Bus bus = (Bus) dao.create();
                bus.setNamePicking("Люкс");
                bus.setColor((Color)colorDao.getByIndex(random.nextInt(colorDao.getCount())));
                bus.setNumberPassengerSeats(random.nextInt(20) + 20);
                bus.setNumberPassengerStanding(random.nextInt(20) + 20);
                bus.setMark((Mark)markDao.getByIndex(random.nextInt(7)+15)); //Только грузовые и автобусы
                bus.setReleaseYear(new Date());
                bus.setVin(String.valueOf(Math.abs(random.nextLong())));
                //ThreadLocalRandom.current().nextInt();
                dao.add(bus);
            }
            dao.saveToFile();
        }
    }
}
