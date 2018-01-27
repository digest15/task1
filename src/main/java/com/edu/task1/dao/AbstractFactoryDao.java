package com.edu.task1.dao;

/**
 * Created by damager on 15.12.17.
 */
public abstract class AbstractFactoryDao implements FactoryDao {
    protected PersisterDao persister;

    private CarServiceDao carServiceDao;
    private ColorDao colorDao;
    private CarDao carDao;
    private BusDao busDao;
    private TruckDao truckDao;
    private MarkDao markDao;
    private MehanicDao mehanicDao;
    private RepairDao repairDao;

    @Override

    public CarServiceDao getCarServiceDao() {
        if (carServiceDao == null) {
            carServiceDao = new CarServiceDao(persister);
        }
        return carServiceDao;
    }

    @Override
    public ColorDao getColorDao() {
        if (colorDao == null) {
            colorDao = new ColorDao(persister);
        }
        return colorDao;
    }

    @Override
    public CarDao getCarDao() {
        if (carDao == null) {
            carDao = new CarDao(persister);
        }
        return carDao;

    }

    @Override
    public BusDao getBusDao() {
        if (busDao == null) {
            busDao = new BusDao(persister);
        }
        return busDao;
    }

    @Override
    public TruckDao getTruckDao() {
        if (truckDao == null) {
            truckDao = new TruckDao(persister);
        }
        return truckDao;
    }

    @Override
    public MarkDao getMarkDao() {
        if (markDao == null) {
            markDao = new MarkDao(persister);
        }
        return markDao;
    }

    @Override
    public MehanicDao getMehanicDao() {
        if (mehanicDao == null) {
            mehanicDao = new MehanicDao(persister);
        }
        return mehanicDao;
    }

    @Override
    public RepairDao getRepairDao() {
        if (repairDao == null) {
            repairDao = new RepairDao(persister);
        }
        return repairDao;
    }

}
