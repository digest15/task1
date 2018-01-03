package com.edu.task1.threads;

import com.edu.task1.dao.FactoryDao;
import com.edu.task1.dao.GenericDao;
import com.edu.task1.entity.Mark;

public class MarkThread extends AbstractEntityThread {
    public MarkThread(FactoryDao factoryDao) {
        super(factoryDao, "MarkThread");
    }

    @Override
    protected GenericDao createDao() {
        return factoryDao.getMarkDao();
    }

    @Override
    public void run() {
        if (dao.getCount() == 0) {
            //1
            Mark mark = (Mark)dao.create();
            mark.setManufacturer("Audi");
            dao.add(mark);

            //2
            mark = (Mark)dao.create();
            mark.setManufacturer("BMW");
            dao.add(mark);

            //3
            mark = (Mark)dao.create();
            mark.setManufacturer("Chevrolet");
            dao.add(mark);

            //4
            mark = (Mark)dao.create();
            mark.setManufacturer("Ford");
            dao.add(mark);

            //5
            mark = (Mark)dao.create();
            mark.setManufacturer("Honda");
            dao.add(mark);

            //6
            mark = (Mark)dao.create();
            mark.setManufacturer("Hyundai");
            dao.add(mark);

            //7
            mark = (Mark)dao.create();
            mark.setManufacturer("Lexus");
            dao.add(mark);

            //8
            mark = (Mark)dao.create();
            mark.setManufacturer("Kia");
            dao.add(mark);

            //9
            mark = (Mark)dao.create();
            mark.setManufacturer("Mazda");
            dao.add(mark);

            //10
            mark = (Mark)dao.create();
            mark.setManufacturer("Mercedes-Benz");
            dao.add(mark);

            //11
            mark = (Mark)dao.create();
            mark.setManufacturer("Mitsubishi");
            dao.add(mark);

            //12
            mark = (Mark)dao.create();
            mark.setManufacturer("Nissan");
            dao.add(mark);

            //13
            mark = (Mark)dao.create();
            mark.setManufacturer("Opel");
            dao.add(mark);

            //14
            mark = (Mark)dao.create();
            mark.setManufacturer("Toyota");
            dao.add(mark);

            //15
            mark = (Mark)dao.create();
            mark.setManufacturer("Лада");
            dao.add(mark);

            //16
            mark = (Mark)dao.create();
            mark.setManufacturer("Скания");
            dao.add(mark);

            //17
            mark = (Mark)dao.create();
            mark.setManufacturer("Камаз");
            dao.add(mark);

            //18
            mark = (Mark)dao.create();
            mark.setManufacturer("Татра");
            dao.add(mark);

            //19
            mark = (Mark)dao.create();
            mark.setManufacturer("Маз");
            dao.add(mark);

            //20
            mark = (Mark)dao.create();
            mark.setManufacturer("Ман");
            dao.add(mark);

            //21
            mark = (Mark)dao.create();
            mark.setManufacturer("Лиаз");
            dao.add(mark);

            //22
            mark = (Mark)dao.create();
            mark.setManufacturer("ГАЗ");
            dao.add(mark);

            //save
            dao.saveToFile();
        }
    }
}
