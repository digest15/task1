package com.edu.task1.dao;

/**
 * Created by damager on 21.12.17.
 */
public class MyTxtFormatSerializationFactoryDao extends AbstractFactoryDao {
    public MyTxtFormatSerializationFactoryDao() {
        persister = new MyTxtFormatSerializationPersisterDao();
    }
}
