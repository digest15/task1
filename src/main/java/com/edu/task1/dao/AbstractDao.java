package com.edu.task1.dao;

import java.util.List;

public abstract class AbstractDao<T> implements GenericDao<T> {
    private List<T> list;
    private PersisterDao persister;

    public AbstractDao(PersisterDao persister) {
        this.persister = persister;
        if (this.list == null) {
            writeToFile();
        }
    }

    public abstract T create();

    public void add(T object) {
        this.list.add(object);
    }

    public void delete(T object) {
        this.delete(object);
    }

    public void update(T object) {
        int i = this.list.indexOf(object);
        this.list.add(i, object);
    }

    public List<T> getAll() {
        return this.list;
    }

    public void saveToFile() {
        persister.save(this.list);
    }

    public void writeToFile() {
        this.list = persister.load();
    }
}
