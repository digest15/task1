package com.edu.task1.dao;

import com.edu.task1.entity.Color;

public class ColorDao extends AbstractDao<Color> {
    public ColorDao(PersisterDao persister) {
        super(persister);
    }

    @Override
    protected String getFileName() {
        return "./Persist/Colors";
    }

    @Override
    public Color create() {
        return new Color();
    }
}
