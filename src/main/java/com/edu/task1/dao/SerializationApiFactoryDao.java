package com.edu.task1.dao;

public class SerializationApiFactoryDao implements FactoryDao {
    public CarServiceDao getCarServiceDao() {
        PersisterDao persister = new SerializationApiPersisterDao();
        CarServiceDao carServiceDao = new CarServiceDao(persister);
        return carServiceDao;
    }
}
