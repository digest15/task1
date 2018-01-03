package com.edu.task1.dao;

import com.edu.task1.entity.Mechanic;

public class MehanicDao extends AbstractDao<Mechanic> {
    public MehanicDao(PersisterDao persister) {
        super(persister);
    }

    @Override
    protected String getFileName() {
        return "Mehanic";
    }

    @Override
    public Mechanic create() {
        return new Mechanic();
    }
}
