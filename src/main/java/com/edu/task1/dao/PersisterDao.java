package com.edu.task1.dao;

import java.util.List;

public interface PersisterDao {
    public void save(List list, String fileName);

    public List load(String fileName);
}
