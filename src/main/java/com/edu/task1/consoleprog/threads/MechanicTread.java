package com.edu.task1.consoleprog.threads;

import com.edu.task1.dao.FactoryDao;
import com.edu.task1.dao.GenericDao;
import com.edu.task1.entity.Mechanic;

import java.math.BigDecimal;

public class MechanicTread extends AbstractEntityThread {
    public MechanicTread(FactoryDao factoryDao) {
        super(factoryDao, "MechanicTread");
    }

    @Override
    protected GenericDao createDao() {
        return this.factoryDao.getMehanicDao();
    }

    @Override
    public void run() {
        if (this.dao.getCount() == 0) {
            //1
            Mechanic mechanic = (Mechanic)this.dao.create();
            mechanic.setName("Иван");
            mechanic.setLastName("Иванов");
            mechanic.setSalary(new BigDecimal(10000));
            this.dao.add(mechanic);

            //2
            mechanic = (Mechanic)this.dao.create();
            mechanic.setName("Петя");
            mechanic.setLastName("Петров");
            mechanic.setSalary(new BigDecimal(20000));
            this.dao.add(mechanic);

            //3
            mechanic = (Mechanic)this.dao.create();
            mechanic.setName("Саша");
            mechanic.setLastName("Сидоров");
            mechanic.setSalary(new BigDecimal(30000));
            this.dao.add(mechanic);

            //4
            mechanic = (Mechanic)this.dao.create();
            mechanic.setName("Дарма");
            mechanic.setLastName("Бельдыев");
            mechanic.setSalary(new BigDecimal(20000));
            this.dao.add(mechanic);

            //5
            mechanic = (Mechanic)this.dao.create();
            mechanic.setName("Вася");
            mechanic.setLastName("Пупкин");
            mechanic.setSalary(new BigDecimal(5000));
            this.dao.add(mechanic);

            //6
            mechanic = (Mechanic)this.dao.create();
            mechanic.setName("Миша");
            mechanic.setLastName("Малкин");
            mechanic.setSalary(new BigDecimal(30000));
            this.dao.add(mechanic);

            //7
            mechanic = (Mechanic)this.dao.create();
            mechanic.setName("Миша");
            mechanic.setLastName("Палкин");
            mechanic.setSalary(new BigDecimal(40000));
            this.dao.add(mechanic);

            //8
            mechanic = (Mechanic)this.dao.create();
            mechanic.setName("Федор");
            mechanic.setLastName("Федоров");
            mechanic.setSalary(new BigDecimal(50000));
            this.dao.add(mechanic);

            //9
            mechanic = (Mechanic)this.dao.create();
            mechanic.setName("Степан");
            mechanic.setLastName("Степанов");
            mechanic.setSalary(new BigDecimal(25000));
            this.dao.add(mechanic);

            //10
            mechanic = (Mechanic)this.dao.create();
            mechanic.setName("Кеша");
            mechanic.setLastName("Кротов");
            mechanic.setSalary(new BigDecimal(35000));
            this.dao.add(mechanic);

            //save
            this.dao.saveToFile();
        }
    }
}
