package com.edu.task1.consoleprog.threads;

import com.edu.task1.dao.FactoryDao;
import com.edu.task1.dao.GenericDao;

public abstract class AbstractEntityThread implements Runnable {
    protected Thread thread;
    protected FactoryDao factoryDao;
    protected GenericDao dao;

    public AbstractEntityThread(FactoryDao factoryDao, String nameThread) {
        this.factoryDao = factoryDao;
        this.dao = createDao();
        this.thread = new Thread(this, nameThread);
        thread.start();
    }

    protected abstract GenericDao createDao();

    public Thread getThread() {
        return thread;
    }

    public GenericDao getDao() {
        return dao;
    }
}
