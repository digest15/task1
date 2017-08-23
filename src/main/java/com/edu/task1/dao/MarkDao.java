package com.edu.task1.dao;

import com.edu.task1.entity.Mark;

public class MarkDao extends AbstractDao<Mark> {
    public MarkDao(PersisterDao persister) {
        super(persister);
    }

    @Override
    protected String getFileName() {
        return "./Persist/Marks";
    }

    @Override
    public Mark create() {
        return new Mark();
    }
}
