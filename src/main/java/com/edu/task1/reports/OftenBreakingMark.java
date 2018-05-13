package com.edu.task1.reports;

import com.edu.task1.entity.Mark;
import com.edu.task1.entity.Repair;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OftenBreakingMark extends AbstractReport<Repair> {
    Comparator<Multiset.Entry<?>> comparatorMultiset;

    public OftenBreakingMark(List<Repair> list) {
        super(list);
    }

    @Override
    public void execute() {
        Multiset<Mark> marks = HashMultiset.create();
        for (Repair repair : list) {
            marks.add(repair.getMachine().getMark());
        }

        List<Multiset.Entry<Mark>> marksList = Lists.newArrayList(marks.entrySet());
        Collections.sort(marksList, comparatorMultiset.reversed());
        printCollection(marksList, toStringFunc);
    }

    public Comparator<Multiset.Entry<?>> getComparatorMultiset() {
        return comparatorMultiset;
    }

    public void setComparatorMultiset(Comparator<Multiset.Entry<?>> comparatorMultiset) {
        this.comparatorMultiset = comparatorMultiset;
    }

    {
        setTitle("Поломки по маркам");
        setBasement("Конец отчета\n");
        setComparatorMultiset(ReportsFunction.COMPARE_MULTISET_BY_COUNT);
        setToStringFunc(ReportsFunction.TO_STRING_OFTEN_BREAKING_MARK);
    }
}
