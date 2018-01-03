package com.edu.task1.threads;

import com.edu.task1.dao.FactoryDao;
import com.edu.task1.dao.GenericDao;
import com.edu.task1.entity.Color;

public class ColorThread extends AbstractEntityThread {
    public ColorThread(FactoryDao factoryDao) {
        super(factoryDao, "ColorThread");
    }

    @Override
    protected GenericDao createDao() {
        return factoryDao.getColorDao();
    }

    @Override
    public void run() {
        if (dao.getCount() == 0) {
            //1
            Color color = (Color)dao.create();
            color.setName("Красный");
            color.setRgb("255 0 0");
            dao.add(color);

            //2
            color = (Color)dao.create();
            color.setName("Оранжевый");
            color.setRgb("255 128 0");
            dao.add(color);

            //3
            color = (Color)dao.create();
            color.setName("Синий");
            color.setRgb("0 0 255");
            dao.add(color);

            //4
            color = (Color)dao.create();
            color.setName("Фиолетовый");
            color.setRgb("128 0 255");
            dao.add(color);

            //5
            color = (Color)dao.create();
            color.setName("Белый");
            color.setRgb("255 255 255");
            dao.add(color);

            //6
            color = (Color)dao.create();
            color.setName("Черный");
            color.setRgb("0 0 0");
            dao.add(color);

            //7
            color = (Color)dao.create();
            color.setName("Серый");
            color.setRgb("128 128 128");
            dao.add(color);

            //8
            color = (Color)dao.create();
            color.setName("Зеленый");
            color.setRgb("0 255 0");
            dao.add(color);

            //save
            dao.saveToFile();
        }    
    }
}
