package com.edu.task1.reports;

import com.edu.task1.entity.Repair;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;

public class SummaByMark extends AbstractReport<Repair> {
    private Comparator<Map.Entry<?, ? extends Number>> comparatorMap;

    public SummaByMark(List<Repair> list) {
        super(list);
    }

    @Override
    public void execute() {
        Map<String, BigDecimal> summaByMarks = new HashMap<>();
        for (Repair repair : list) {
            String marka = repair.getMachine().getMark().getManufacturer();
            BigDecimal amount = repair.getAmount();
            BigDecimal summ = summaByMarks.get(marka);

            summaByMarks.put(marka, amount.add(summ == null ? new BigDecimal(0) : summ));
        }

        List<Map.Entry<String, BigDecimal>> listSummaByMarks = Lists.newArrayList(summaByMarks.entrySet());
        Collections.sort(listSummaByMarks, comparatorMap.reversed());
        printCollection(listSummaByMarks, toStringFunc);
    }

    public Comparator<Map.Entry<?, ? extends Number>> getComparatorMap() {
        return comparatorMap;
    }

    public void setComparatorMap(Comparator<Map.Entry<?, ? extends Number>> comparatorMap) {
        this.comparatorMap = comparatorMap;
    }


    {
        setTitle("Подсчитать сумму затрат по маркам автомобиля");
        setBasement("Конец отчета\n");
        setComparatorMap(ReportsFunction.COMPARE_MAP_BY_VALUE);
        setToStringFunc(ReportsFunction.TO_STRING_SUMMA_BY_VALUE);
    }
}
