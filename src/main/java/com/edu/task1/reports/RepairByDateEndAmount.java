package com.edu.task1.reports;

import com.edu.task1.entity.Repair;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class RepairByDateEndAmount extends AbstractReport<Repair> {
    private Comparator<Repair> compareByDate;
    private Comparator<Repair> compareByAmount;

    public RepairByDateEndAmount(List<Repair> list) {
        super(list);
    }

    @Override
    public void execute() {
        Collections.sort(list, compareByDate);
        Collections.sort(list, compareByAmount);
        printCollection(list, toStringFunc);
    }

    public void setCompareByDate(Comparator<Repair> compareByDate) {
        this.compareByDate = compareByDate;
    }

    public void setCompareByAmount(Comparator<Repair> compareByAmount) {
        this.compareByAmount = compareByAmount;
    }

    public Comparator<Repair> getCompareByDate() {
        return compareByDate;
    }

    public Comparator<Repair> getCompareByAmount() {
        return compareByAmount;
    }


    {
        setTitle("Ремонты отсортированные по дате, по стоимости. По возрастанию стоимости");
        setBasement("Конец отчета\n");
        setCompareByAmount(ReportsFunction.comparators_repair.BY_AMOUNT);
        setCompareByDate(ReportsFunction.comparators_repair.BY_DATE);
        setToStringFunc(ReportsFunction.TO_STRING_REPAIR);
    }
}
