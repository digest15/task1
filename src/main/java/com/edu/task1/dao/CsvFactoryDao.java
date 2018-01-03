package com.edu.task1.dao;

public class CsvFactoryDao extends AbstractFactoryDao {
    public CsvFactoryDao() {
        persister = new CsvPersisterDAO();
    }
}
