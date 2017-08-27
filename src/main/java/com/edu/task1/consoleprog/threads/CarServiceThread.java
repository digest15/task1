package com.edu.task1.consoleprog.threads;

import com.edu.task1.dao.FactoryDao;
import com.edu.task1.dao.GenericDao;
import com.edu.task1.entity.CarService;

public class CarServiceThread extends AbstractEntityThread {
    public CarServiceThread(FactoryDao factoryDao) {
        super(factoryDao, "CarServiceThread");
    }

    @Override
    protected GenericDao createDao() {
        return this.factoryDao.getCarServiceDao();
    }

    @Override
    public void run() {
        if (this.dao.getCount() == 0) {
            //1
            CarService carService = (CarService)this.dao.create();
            carService.setAddress("г.Иркутск, Александровское шоссе 11 км");
            carService.setName("Александровское");
            carService.setOpeningTime(10);
            carService.setClosingTime(20);
            this.dao.add(carService);

            //2
            carService = (CarService)this.dao.create();
            carService.setAddress("г.Иркутск, ул. Ширямова 10");
            carService.setName("На Ширямва");
            carService.setOpeningTime(9);
            carService.setClosingTime(19);
            this.dao.add(carService);

            //save
            this.dao.saveToFile();
        }
    }
}
