package com.edu.task1.threads;

import com.edu.task1.dao.FactoryDao;
import com.edu.task1.dao.GenericDao;
import com.edu.task1.entity.Color;
import com.edu.task1.entity.Mark;
import com.edu.task1.entity.Truck;

import java.util.Date;
import java.util.Random;

public class TruckThread extends AbstractEntityThread {
    private GenericDao colorDao;
    private GenericDao markDao;
    private Random random;

    public TruckThread(FactoryDao factoryDao, GenericDao colorDao, GenericDao markDao) {
        super(factoryDao, "TruckThread");
        this.colorDao = colorDao;
        this.markDao = markDao;
        this.random = new Random(new Date().getTime());
    }

    @Override
    protected GenericDao createDao() {
        return factoryDao.getTruckDao();
    }

    @Override
    public void run() {
        if (dao.getCount() == 0) {
            Truck truck;
            for (int i=0;i <=1000;i++) {
                truck = (Truck) dao.create();
                truck.setNamePicking("Люкс");
                truck.setNumberAxle(random.nextInt(6) + 2);
                truck.setColor((Color)colorDao.getByIndex(random.nextInt(colorDao.getCount())));
                truck.setMark((Mark)markDao.getByIndex(random.nextInt(7)+15)); //Только грузовые и автобусы
                truck.setReleaseYear(new Date());
                truck.setVin(String.valueOf(Math.abs(random.nextLong())));
                //ThreadLocalRandom.current().nextInt();
                dao.add(truck);
            }
            dao.saveToFile();
        }
    }
}
