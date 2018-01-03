package com.edu.task1.dao;

/**
 * Created by damager on 15.12.17.
 */
public abstract class AbstractFactoryDao implements FactoryDao {
    protected PersisterDao persister;

    @Override
    public CarServiceDao getCarServiceDao() {
        return new CarServiceDao(persister);
    }

    @Override
    public ColorDao getColorDao() {
        return new ColorDao(persister);
    }

    @Override
    public CarDao getCarDao() {
        return new CarDao(persister);
    }

    @Override
    public BusDao getBusDao() {
        return new BusDao(persister);
    }

    @Override
    public TruckDao getTruckDao() {
        return new TruckDao(persister);
    }

    @Override
    public MarkDao getMarkDao() {
        return new MarkDao(persister);
    }

    @Override
    public MehanicDao getMehanicDao() {
        return new MehanicDao(persister);
    }

    @Override
    public RepairDao getRepairDao() {
        return new RepairDao(persister);
    }

}
