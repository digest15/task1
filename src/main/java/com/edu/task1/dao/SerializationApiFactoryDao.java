package com.edu.task1.dao;

public class SerializationApiFactoryDao implements FactoryDao {
    private PersisterDao persister;

    public SerializationApiFactoryDao() {
        this.persister = new SerializationApiPersisterDao();
    }

    @Override
    public CarServiceDao getCarServiceDao() {
        return new CarServiceDao(this.persister);
    }

    @Override
    public ColorDao getColorDao() {
        return new ColorDao(this.persister);
    }

    @Override
    public CarDao getCarDao() {
        return new CarDao(this.persister);
    }

    @Override
    public BusDao getBusDao() {
        return new BusDao(this.persister);
    }

    @Override
    public TruckDao getTruckDao() {
        return new TruckDao(this.persister);
    }

    @Override
    public MarkDao getMarkDao() {
        return new MarkDao(this.persister);
    }

    @Override
    public MehanicDao getMehanicDao() {
        return new MehanicDao(this.persister);
    }

    @Override
    public RepairDao getRepairDao() {
        return new RepairDao(this.persister);
    }
}
