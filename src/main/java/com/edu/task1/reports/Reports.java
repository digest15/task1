package com.edu.task1.reports;

import com.edu.task1.dao.GenericDao;
import com.edu.task1.entity.CarService;
import com.edu.task1.entity.Repair;

import java.util.function.Consumer;

/**
 * Created by damager on 27.01.18.
 */
public class Reports {
    private static void allReportsByRepiar(GenericDao<Repair> repairDao, GenericDao<CarService> carServiceDao, ReportsFunction.pritnTo pritnTo) {
        String reportTitle = "";

        reportTitle = "----Ремонты отсортированные по дате, по стоимости. По возрастанию стоимости----";
        ReportsFunction.reportByDateEndAmount(repairDao.getAll(), ReportsFunction.REPAIR_COMPARE_BY_AMOUNT, reportTitle, pritnTo);

        reportTitle = "----Ремонты отсортированные по дате, по стоимости. По убыванию стоимости----";
        ReportsFunction.reportByDateEndAmount(repairDao.getAll(), ReportsFunction.REPAIR_COMPARE_BY_AMOUNT.reversed(), reportTitle, pritnTo);

        reportTitle = "----Самый ломающийся автомобиль----";
        ReportsFunction.oftenBreakingCar(repairDao.getAll(), reportTitle, pritnTo);

        reportTitle = "----Поломки по маркам----";
        ReportsFunction.oftenBreakingMark(repairDao.getAll(), reportTitle, pritnTo);

        reportTitle = "----Подсчитать сумму затрат по маркам автомобиля----";
        ReportsFunction.summaByMark(repairDao.getAll(), reportTitle, pritnTo);

        reportTitle = "----доходы автосервисов по кварталам----";
        ReportsFunction.revenuesForQuarter(repairDao.getAll(), reportTitle, pritnTo);

        reportTitle = "----Подсчитать сумму затрат по маркам автомобиля по кварталам----";
        ReportsFunction.summaByMarkForQuarter(repairDao.getAll(), reportTitle, pritnTo);

        reportTitle = "----Автосервис и его сотрудники----";
        ReportsFunction.workerOfServise(repairDao.getAll(), carServiceDao.getAll(), reportTitle, pritnTo);
    }

    public static void allReportsByRepiarToConsole(GenericDao<Repair> repairDao, GenericDao<CarService> carServiceDao) {
        allReportsByRepiar(repairDao, carServiceDao, ReportsFunction.pritnTo.CONSOLE);
    }

    public static void allReportsByRepiarToPdf(GenericDao<Repair> repairDao, GenericDao<CarService> carServiceDao) {
        allReportsByRepiar(repairDao, carServiceDao, ReportsFunction.pritnTo.PDF);
    }
}
