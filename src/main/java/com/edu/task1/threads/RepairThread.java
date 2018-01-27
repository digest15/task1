package com.edu.task1.threads;

import com.edu.task1.dao.FactoryDao;
import com.edu.task1.dao.GenericDao;
import com.edu.task1.entity.CarService;
import com.edu.task1.entity.Machine;
import com.edu.task1.entity.Mechanic;
import com.edu.task1.entity.Repair;
import com.edu.task1.helpers.DateHelper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RepairThread extends AbstractEntityThread {
    private List<Machine> machineList;
    private GenericDao carServiceDao;
    private GenericDao mechanicDao;
    private Random random;


    public RepairThread(FactoryDao factoryDao, GenericDao carServiceDao, GenericDao mechanicDao, List<Machine> machineList) {
        super(factoryDao, "RepairThread");
        this.random = new Random(new Date().getTime());
        this.carServiceDao = carServiceDao;
        this.mechanicDao = mechanicDao;
        this.machineList = machineList;
    }

    @Override
    protected GenericDao createDao() {
        return factoryDao.getRepairDao();
    }

    @Override
    public void run() {
        if (dao.getCount() == 0) {
            Repair repair;
            for (int i=0; i < 10000; i++) {
                repair = (Repair) dao.create();
                repair.setDateTime(DateHelper.randomDate());
                repair.setCarServise((CarService)carServiceDao.getByIndex(random.nextInt(2)));
                repair.setMechanic((Mechanic)mechanicDao.getByIndex(random.nextInt(mechanicDao.getCount())));
                repair.setMachine((Machine) machineList.get(random.nextInt(machineList.size())));
                repair.setAmount(new BigDecimal(random.nextDouble()));
                dao.add(repair);
            }
            //dao.saveToFile();
        }
    }
}
