package com.edu.task1.dao;

import java.util.List;

public interface PersisterDao {
    public String catalogName = "Persist";

    public void save(List list, String fileName);

    public List load(String fileName);
}
