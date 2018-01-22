package com.edu.task1.dao;

/**
 * Created by damager on 22.01.18.
 */
public class XstreamFactoryDao extends AbstractFactoryDao {
    public XstreamFactoryDao() {
        persister = new XstreamPersisterDao();
    }
}
