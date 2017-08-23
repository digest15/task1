package com.edu.task1.dao;

public interface FactoryDao {
    public CarServiceDao getCarServiceDao();

    public ColorDao getColorDao();

    public CarDao getCarDao();

    public BusDao getBusDao();

    public TruckDao getTruckDao();

    public MarkDao getMarkDao();

    public MehanicDao getMehanicDao();

    public RepairDao getRepairDao();

}
