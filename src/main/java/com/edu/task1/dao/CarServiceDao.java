package com.edu.task1.dao;

import com.edu.task1.entity.CarService;

public class CarServiceDao extends AbstractDao<CarService> {

    public CarServiceDao(PersisterDao persister) {
        super(persister);
    }

    @Override
    protected String getFileName() {
        return "./Persist/CarServices";
    }

    @Override
    public CarService create() {
        return new CarService();
    }
}
