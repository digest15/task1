package com.edu.task1.reports;

import com.edu.task1.entity.*;
import com.edu.task1.helpers.DateHelper;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by damager on 24.01.18.
 */
public class ReportsFunction {
    public static final Comparator<Repair> REPAIR_COMPARE_BY_DATE = new Comparator<Repair>() {
        @Override
        public int compare(Repair o1, Repair o2) {
            return o1.getDateTime().compareTo(o2.getDateTime());
        }
    };

    public static final Comparator<Repair> REPAIR_COMPARE_BY_AMOUNT = new Comparator<Repair>() {
        @Override
        public int compare(Repair o1, Repair o2) {
            return o1.getAmount().compareTo(o2.getAmount());
        }
    };

    public static Function<Object, String> REPAIR_TO_STRING = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Repair repair = (Repair) o;
            StringBuilder str = new StringBuilder("Date: ").append(repair.getDateTime()).append("\n");

            str.append("Car Service: ").append(repair.getCarServise().getName()).append("\n");

            Machine machine = repair.getMachine();
            str.append("Machine: ").append(machine.getMark().getManufacturer()).append("; ").append(machine.getColor().getName());
            str.append("; ").append(machine.getVin()).append("; \n");

            Mechanic mechanic = repair.getMechanic();
            str.append("Mechanic: ").append(mechanic.getName()).append("\n");
            str.append("Amount: ").append(repair.getAmount()).append("\n");
            return str.toString();
        }
    };

    public static Comparator<Map.Entry<?, ? extends Number>> MAP_COMPARE_BY_VALUE = new Comparator<Map.Entry<?, ? extends Number>>() {
        @Override
        public int compare(Map.Entry<?, ? extends Number> o1, Map.Entry<?, ? extends Number> o2) {
            BigDecimal val1 = new BigDecimal(o1.getValue().toString());
            BigDecimal val2 = new BigDecimal(o2.getValue().toString());
            return val1.compareTo(val2);
        }
    };

    public static Comparator<Multiset.Entry<?>> MULTISET_COMPARE_BY_COUNT = new Comparator<Multiset.Entry<?>>() {
        @Override
        public int compare(Multiset.Entry<?> o1, Multiset.Entry<?> o2) {
            return Ints.compare(o1.getCount(), o2.getCount());
        }
    };

    public static Function<Object, String> OFTEN_BREAKING_CAR_TO_STRING = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Map.Entry<Machine, Integer> entry = (Map.Entry<Machine, Integer>) o;
            //StringBuilder
            Machine machine = entry.getKey();
            String str = "Марка: " + machine.getMark().getManufacturer() + "\n";
            str += "VIN: " + machine.getVin() + "\n";
            str += "Колво поломок: " + Integer.valueOf(entry.getValue());

            return str;
        }
    };

    public static Function<Object, String> OFTEN_BREAKING_MARK_TO_STRING = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Multiset.Entry<Mark> entry = (Multiset.Entry<Mark>) o;

            String str = entry.getElement().getManufacturer() + ", Количество поломок: ";
            str += Integer.valueOf(entry.getCount());

            return str;
        }
    };

    public static Function<Object, String> SUMMA_BY_VALUE_TO_STRING = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Map.Entry<String, BigDecimal> entry = (Map.Entry<String, BigDecimal>) o;
            StringBuilder stringBuilder = new StringBuilder(entry.getKey());
            stringBuilder.append(", Сумма: ");
            stringBuilder.append(getBigDecimalValue(entry.getValue()));
            return stringBuilder.toString();
        }
    };

    public static Function<Object, String> SUMMA_BY_REPORTTABLE_TO_STRING = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Map.Entry<ReportTable, BigDecimal> entry = (Map.Entry<ReportTable, BigDecimal>) o;
            StringBuilder stringBuilder = new StringBuilder(entry.getKey().toString());
            stringBuilder.append(", Сумма: ");
            stringBuilder.append(getBigDecimalValue(entry.getValue()));

            return stringBuilder.toString();
        }
    };

    public static Function<Object, String> MECHANIC_TO_STRING = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Mechanic mechanic = (Mechanic) o;

            String str = mechanic.getName() + " " + mechanic.getLastName() + " - " + mechanic.getSalary();

            return str;
        }
    };

    public static enum pritnTo implements Consumer<String> {
        CONSOLE {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        },

        PDF {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        }
    }

    public static void reportByDateEndAmount(List<Repair> list, Comparator compareByAmount, String reportTitle, Consumer<String> prinTo) {
        Collections.sort(list, REPAIR_COMPARE_BY_DATE);
        Collections.sort(list, compareByAmount);
        printCollection(list, reportTitle, REPAIR_TO_STRING, prinTo);
    }

    public static void oftenBreakingCar(List<Repair> listRepair, String reportTitle, Consumer<String> printTo) {
        if(CollectionUtils.isEmpty(listRepair))
            return;

        List<Machine> machineList = new ArrayList<>(listRepair.size());
        for (Repair repiar : listRepair) {
            machineList.add(repiar.getMachine());
        }

        Map<Machine, Integer> countBreaking = new IdentityHashMap<>();
        for (Machine machine : machineList) {
            countBreaking.put(machine, Collections.frequency(machineList, machine));
        }

        List<Map.Entry<Machine, Integer>> listEntry = new ArrayList<>(countBreaking.entrySet());
        Collections.sort(listEntry, MAP_COMPARE_BY_VALUE);

        Map.Entry<Machine, Integer> maxBreaking = listEntry.get(0);
        List<Map.Entry<Machine, Integer>> listEntryCopy = new ArrayList<>();
        for (Map.Entry<Machine, Integer> entry : listEntry) {
            if (entry.getValue().equals(maxBreaking.getValue())) {
                listEntryCopy.add(entry);
            }
        }

        printCollection(listEntryCopy, reportTitle, OFTEN_BREAKING_CAR_TO_STRING, printTo);
    }

    public static void oftenBreakingMark(List<Repair> listRepair, String reportTitle, Consumer<String> printTo) {
        Multiset<Mark> marks = HashMultiset.create();
        for (Repair repair : listRepair) {
            marks.add(repair.getMachine().getMark());
        }

        List<Multiset.Entry<Mark>> marksList = Lists.newArrayList(marks.entrySet());
        Collections.sort(marksList, MULTISET_COMPARE_BY_COUNT.reversed());
        printCollection(marksList, reportTitle, OFTEN_BREAKING_MARK_TO_STRING, printTo);
    }

    public static void summaByMark(List<Repair> listRepair, String reportTitle, Consumer<String> printTo) {
        Map<String, BigDecimal> summaByMarks = new HashMap<>();
        for (Repair repair : listRepair) {
            String marka = repair.getMachine().getMark().getManufacturer();
            BigDecimal amount = repair.getAmount();
            BigDecimal summ = summaByMarks.get(marka);

            summaByMarks.put(marka, amount.add(summ == null ? new BigDecimal(0) : summ));
        }

        List<Map.Entry<String, BigDecimal>> listSummaByMarks = Lists.newArrayList(summaByMarks.entrySet());
        Collections.sort(listSummaByMarks, MAP_COMPARE_BY_VALUE.reversed());
        printCollection(listSummaByMarks, reportTitle, SUMMA_BY_VALUE_TO_STRING, printTo);
    }

    public static void revenuesForQuarter(List<Repair> listRepair, String reportTitle, Consumer<String> printTo) {
        Map<String, BigDecimal> summaByServise = new HashMap<>();
        for (Repair repair : listRepair) {
            String quarter = DateHelper.fistQuarter(repair.getDateTime());
            BigDecimal amount = repair.getAmount();
            BigDecimal summ = summaByServise.get(quarter);

            summaByServise.put(quarter, amount.add(summ == null ? new BigDecimal(0) : summ));
        }

        List<Map.Entry<String, BigDecimal>> listSummaByMarks = Lists.newArrayList(summaByServise.entrySet());
        Collections.sort(listSummaByMarks, MAP_COMPARE_BY_VALUE.reversed());
        printCollection(listSummaByMarks, reportTitle, SUMMA_BY_VALUE_TO_STRING, printTo);
    }

    public static void summaByMarkForQuarter(List<Repair> listRepair, String reportTitle, Consumer<String> printTo) {
        Map<ReportTable, BigDecimal> map = new HashMap<>();
        for (Repair repair : listRepair) {
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
                if(o1 == o2) return 0;
                return o1.getKey().hashCode() - o2.getKey().hashCode();
            }
        });

        Collections.sort(listSummaByMarks, MAP_COMPARE_BY_VALUE.reversed());

        printCollection(listSummaByMarks, reportTitle, SUMMA_BY_REPORTTABLE_TO_STRING, printTo);
    }

    public static void workerOfServise(List<Repair> listRepair, List<CarService> listServise, String reportTitle, Consumer<String> printTo) {
        Multimap<String, Mechanic> map = HashMultimap.create();
        for (Repair repair : listRepair) {
            Mechanic mechanic = repair.getMechanic();
            CarService service = repair.getCarServise();
            map.put(service.getName(), mechanic);
        }

        printTo.accept(reportTitle);
        for (String servise : map.keySet()) {
            printTo.accept(servise);
            Collection<Mechanic> mechanics = map.get(servise);
            List<Mechanic> mechanicsList = Lists.newArrayList(mechanics);
            printCollection(mechanicsList, MECHANIC_TO_STRING, printTo);
        }
    }

    private static void printCollection(Collection<?> list, String reportTitle, Function<Object, String> functionToString, Consumer<String> functionPrint) {
        functionPrint.accept(reportTitle);

        printCollection(list, functionToString, functionPrint);
    }

    private static void printCollection(Collection<?> list, Function<Object, String> functionToString, Consumer<String> functionPrint) {
        int i = 1;
        for (Object o : list) {
            functionPrint.accept(i + ". " + functionToString.apply(o));
            i++;
        }
    }

    private static class ReportTable {
        private String time;
        private String mark;

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
    }

    ;

    private static String getBigDecimalValue(BigDecimal input) {
        final NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setGroupingUsed(true);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(4);
        return numberFormat.format(input);
    }
}
