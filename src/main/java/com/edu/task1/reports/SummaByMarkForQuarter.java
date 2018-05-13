package com.edu.task1.reports;

import com.edu.task1.entity.Repair;
import com.edu.task1.helpers.DateHelper;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

public class SummaByMarkForQuarter extends AbstractReport<Repair> {
    private Comparator<Map.Entry<?, ? extends Number>> comparatorMap;

    public Function<Object, String> TO_STRING_SUMMA_BY_REPORTTABLE = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Map.Entry<ReportTable, BigDecimal> entry = (Map.Entry<ReportTable, BigDecimal>) o;

            String str = entry.getKey() + ", Сумма: ";
            str += entry.getValue().toString() + "\n";

            return str;
        }
    };

    public SummaByMarkForQuarter(List<Repair> list) {
        super(list);
    }

    @Override
    public void execute() {
        Map<ReportTable, BigDecimal> map = new HashMap<>();
        for (Repair repair : list) {
            String marka = repair.getMachine().getMark().getManufacturer();
            String time = DateHelper.fistQuarter(repair.getDateTime());
            ReportTable reportTable = new ReportTable(time, marka);

            BigDecimal amount = repair.getAmount();
            BigDecimal summ = map.get(reportTable);

            map.put(reportTable, amount.add(summ == null ? new BigDecimal(0) : summ));
        }

        List<Map.Entry<ReportTable, BigDecimal>> listSummaByMarks = Lists.newArrayList(map.entrySet());

        Collections.sort(listSummaByMarks, new Comparator<Map.Entry<ReportTable, BigDecimal>>() {
            @Override
            public int compare(Map.Entry<ReportTable, BigDecimal> o1, Map.Entry<ReportTable, BigDecimal> o2) {
                return o1.getKey().hashCode() - o2.getKey().hashCode();
            }
        });

        Collections.sort(listSummaByMarks, comparatorMap.reversed());

        printCollection(listSummaByMarks, toStringFunc);
    }

    public Comparator<Map.Entry<?, ? extends Number>> getComparatorMap() {
        return comparatorMap;
    }

    public void setComparatorMap(Comparator<Map.Entry<?, ? extends Number>> comparatorMap) {
        this.comparatorMap = comparatorMap;
    }


    private static class ReportTable {
        public String time;
        public String mark;

        public ReportTable(String time, String mark) {
            this.time = time;
            this.mark = mark;
        }

        @Override
        public String toString() {
            return mark + ", " + time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ReportTable that = (ReportTable) o;

            if (time != null ? !time.equals(that.time) : that.time != null) return false;
            return mark != null ? mark.equals(that.mark) : that.mark == null;

        }

        @Override
        public int hashCode() {
            int result = time != null ? time.hashCode() : 0;
            result = 31 * result + (mark != null ? mark.hashCode() : 0);
            return result;
        }
    };


    {
        setTitle("Подсчитать сумму затрат по маркам автомобиля по кварталам");
        setBasement("Конец отчета\n");
        setComparatorMap(ReportsFunction.COMPARE_MAP_BY_VALUE);
        setToStringFunc(TO_STRING_SUMMA_BY_REPORTTABLE);
    }
}
