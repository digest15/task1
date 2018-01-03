package com.edu.task1.dao;

import java.util.List;

public abstract class AbstractDao<T> implements GenericDao<T> {
    private List<T> list;
    private PersisterDao persister;


    protected abstract String getFileName();

    public AbstractDao(PersisterDao persister) {
        this.persister = persister;
        if (this.list == null) {
            readFromFile();
        }
    }

    @Override
    public void add(T object) {
        list.add(object);
    }

    @Override
    public void delete(T object) {

    }

    @Override
    public void update(T object) {
        int i = this.list.indexOf(object);
        list.add(i, object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getByIndex(int index) {
        return list.get(index);
    }

    @Override
    public List<T> getAll() {
        return list;
    }

    public void saveToFile() {
        persister.save(list, getFileName());
    }

    public void readFromFile() {
        list = persister.load(getFileName());
    }
}
