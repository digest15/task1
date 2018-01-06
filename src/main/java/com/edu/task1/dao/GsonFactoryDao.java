package com.edu.task1.dao;

/**
 * Created by damager on 03.01.18.
 */
public class GsonFactoryDao extends AbstractFactoryDao {
    public GsonFactoryDao() {
        persister = new GsonPersisterDao();
    }
}
