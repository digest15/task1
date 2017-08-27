package com.edu.task1.consoleprog.threads;

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
        return this.factoryDao.getTruckDao();
    }

    @Override
    public void run() {
        if (this.dao.getCount() == 0) {
            Truck truck;
            for (int i=0;i <=1000;i++) {
                truck = (Truck) this.dao.create();
                truck.setNamePicking("Люкс");
                truck.setNumberAxle(random.nextInt(6) + 2);
                truck.setColor((Color)this.colorDao.getByIndex(random.nextInt(this.colorDao.getCount())));
                truck.setMark((Mark)this.markDao.getByIndex(random.nextInt(7)+15)); //Только грузовые и автобусы
                truck.setReleaseYear(new Date());
                truck.setVin(String.valueOf(random.nextLong()));
                //ThreadLocalRandom.current().nextInt();
                this.dao.add(truck);
            }
            this.dao.saveToFile();
        }
    }
}
