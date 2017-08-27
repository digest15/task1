package com.edu.task1.consoleprog.threads;

import com.edu.task1.dao.FactoryDao;
import com.edu.task1.dao.GenericDao;
import com.edu.task1.entity.Mark;

public class MarkThread extends AbstractEntityThread {
    public MarkThread(FactoryDao factoryDao) {
        super(factoryDao, "MarkThread");
    }

    @Override
    protected GenericDao createDao() {
        return this.factoryDao.getMarkDao();
    }

    @Override
    public void run() {
        if (this.dao.getCount() == 0) {
            //1
            Mark mark = (Mark)this.dao.create();
            mark.setManufacturer("Audi");
            this.dao.add(mark);

            //2
            mark = (Mark)this.dao.create();
            mark.setManufacturer("BMW");
            this.dao.add(mark);

            //3
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Chevrolet");
            this.dao.add(mark);

            //4
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Ford");
            this.dao.add(mark);

            //5
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Honda");
            this.dao.add(mark);

            //6
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Hyundai");
            this.dao.add(mark);

            //7
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Lexus");
            this.dao.add(mark);

            //8
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Kia");
            this.dao.add(mark);

            //9
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Mazda");
            this.dao.add(mark);

            //10
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Mercedes-Benz");
            this.dao.add(mark);

            //11
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Mitsubishi");
            this.dao.add(mark);

            //12
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Nissan");
            this.dao.add(mark);

            //13
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Opel");
            this.dao.add(mark);

            //14
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Toyota");
            this.dao.add(mark);

            //15
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Лада");
            this.dao.add(mark);

            //16
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Скания");
            this.dao.add(mark);

            //17
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Камаз");
            this.dao.add(mark);

            //18
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Татра");
            this.dao.add(mark);

            //19
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Маз");
            this.dao.add(mark);

            //20
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Ман");
            this.dao.add(mark);

            //21
            mark = (Mark)this.dao.create();
            mark.setManufacturer("Лиаз");
            this.dao.add(mark);

            //22
            mark = (Mark)this.dao.create();
            mark.setManufacturer("ГАЗ");
            this.dao.add(mark);

            //save
            this.dao.saveToFile();
        }
    }
}
