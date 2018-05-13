package com.edu.task1.reports;

import com.edu.task1.entity.Repair;
import com.edu.task1.helpers.DateHelper;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;

public class RevenuesForQuarter extends AbstractReport<Repair> {
    private Comparator<Map.Entry<?, ? extends Number>> comparatorMap;

    public RevenuesForQuarter(List<Repair> list) {
        super(list);
    }

    @Override
    public void execute() {
        Map<String, BigDecimal> summaByServise = new HashMap<>();
        for (Repair repair : list) {
            String quarter = DateHelper.fistQuarter(repair.getDateTime());
            BigDecimal amount = repair.getAmount();
            BigDecimal summ = summaByServise.get(quarter);

            summaByServise.put(quarter, amount.add(summ == null ? new BigDecimal(0) : summ));
        }

        List<Map.Entry<String, BigDecimal>> listSummaByMarks = Lists.newArrayList(summaByServise.entrySet());
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
        setTitle("доходы автосервисов по кварталам");
        setBasement("Конец отчета\n");
        setComparatorMap(ReportsFunction.COMPARE_MAP_BY_VALUE);
        setToStringFunc(ReportsFunction.TO_STRING_SUMMA_BY_VALUE);
    }
}
