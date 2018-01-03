package com.edu.task1.dao;

import com.edu.task1.entity.Truck;

public class TruckDao extends AbstractDao<Truck> {
    public TruckDao(PersisterDao persister) {
        super(persister);
    }

    @Override
    protected String getFileName() {
        return "Truck";
    }

    @Override
    public Truck create() {
        return new Truck();
    }
}
