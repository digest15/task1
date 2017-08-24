package com.edu.task1.dao;

import java.util.List;

public interface GenericDao<T> {

    public <T> T create();

    public void add(T object);

    public void delete(T object);

    public void update(T object);

    public void saveToFile();

    public void readFromFile();

    public List<T> getAll();

    public T getByIndex(int index);
}