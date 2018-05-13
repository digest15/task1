package com.edu.task1.reports;

import com.edu.task1.entity.*;
import com.edu.task1.helpers.DateHelper;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by damager on 24.01.18.
 */
public class ReportsFunction {

    //Comparators
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static enum comparators_repair implements Comparator<Repair> {

        BY_DATE {
            @Override
            public int compare(Repair o1, Repair o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        },

        BY_AMOUNT {
            @Override
            public int compare(Repair o1, Repair o2) {
                return o1.getAmount().compareTo(o2.getAmount());
            }
        }
    }

    public static Comparator<Map.Entry<?, ? extends Number>> COMPARE_MAP_BY_VALUE = new Comparator<Map.Entry<?, ? extends Number>>() {
        @Override
        public int compare(Map.Entry<?, ? extends Number> o1, Map.Entry<?, ? extends Number> o2) {
            BigDecimal val1 = new BigDecimal(o1.getValue().toString());
            BigDecimal val2 = new BigDecimal(o2.getValue().toString());
            return val1.compareTo(val2);
        }
    };

    public static Comparator<Multiset.Entry<?>> COMPARE_MULTISET_BY_COUNT = new Comparator<Multiset.Entry<?>>() {
        @Override
        public int compare(Multiset.Entry<?> o1, Multiset.Entry<?> o2) {
            return Ints.compare(o1.getCount(), o2.getCount());
        }
    };


    //ToString
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Function<Object, String> TO_STRING_REPAIR = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Repair repair = (Repair) o;

            String str = "Date: " + repair.getDateTime() + "\n";

            str += "Car Service: " + repair.getCarServise().getName() + "\n";

            Machine machine = repair.getMachine();
            str += "Machine: " + machine.getMark().getManufacturer() + "; " + machine.getColor().getName() + "; " + machine.getVin() + "; " + "\n";

            Mechanic mechanic = repair.getMechanic();
            str += "Mechanic: " + mechanic.getName() + "\n";

            str += "Amount: " + repair.getAmount() + "\n";

            return str;
        }
    };

    public static Function<Object, String> TO_STRING_OFTEN_BREAKING_CAR = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Map.Entry<Machine, Integer> entry = (Map.Entry<Machine, Integer>) o;

            Machine machine = entry.getKey();
            String str = "Марка: " + machine.getMark().getManufacturer() + "\n";
            str += "VIN: " + machine.getVin() + "\n";
            str += "Колво поломок: " + Integer.valueOf(entry.getValue()) + "\n";

            return str;
        }
    };

    public static Function<Object, String> TO_STRING_OFTEN_BREAKING_MARK = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Multiset.Entry<Mark> entry = (Multiset.Entry<Mark>) o;

            String str = entry.getElement().getManufacturer() + ", Количество поломок: ";
            str += Integer.valueOf(entry.getCount()) + "\n";

            return str;
        }
    };

    public static Function<Object, String> TO_STRING_SUMMA_BY_VALUE = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Map.Entry<String, BigDecimal> entry = (Map.Entry<String, BigDecimal>) o;

            String str = entry.getKey() + ", Сумма: ";
            str += entry.getValue().toString() + "\n";

            return str;
        }
    };

    public static Function<Object, String> TO_STRING_MECHANIC = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            Mechanic mechanic = (Mechanic) o;

            String str = mechanic.getName() + " " + mechanic.getLastName() + " - " + mechanic.getSalary() + "\n";

            return str;
        }
    };

}
