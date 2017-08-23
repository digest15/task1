package com.edu.task1.dao;

import com.edu.task1.entity.Car;

public class CarDao extends AbstractDao<Car> {
    public CarDao(PersisterDao persister) {
        super(persister);
    }

    @Override
    protected String getFileName() {
        return "./Persist/Cars";
    }

    @Override
    public Car create() {
        return new Car();
    }
}
