package com.edu.task1.dao;

public class SerializationApiFactoryDao extends AbstractFactoryDao {
    public SerializationApiFactoryDao() {
        persister = new SerializationApiPersisterDao();
    }
}
