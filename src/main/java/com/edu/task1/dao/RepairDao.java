package com.edu.task1.dao;

import com.edu.task1.entity.Repair;

public class RepairDao extends AbstractDao<Repair> {
    public RepairDao(PersisterDao persister) {
        super(persister);
    }

    @Override
    protected String getFileName() {
        return "Repair";
    }

    @Override
    public Repair create() {
        return new Repair();
    }
}
