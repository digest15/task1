package com.edu.task1.dao;

import com.edu.task1.entity.Bus;

public class BusDao extends AbstractDao<Bus> {
    public BusDao(PersisterDao persister) {
        super(persister);
    }

    @Override
    protected String getFileName() {
        return "Bus";
    }

    @Override
    public Bus create() {
        return new Bus();
    }
}
