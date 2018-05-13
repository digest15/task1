package com.edu.task1.reports;

import com.edu.task1.entity.Machine;
import com.edu.task1.entity.Repair;

import java.util.*;

public class OftenBreakingCar extends AbstractReport<Repair> {
    private Comparator<Map.Entry<?, ? extends Number>> comparatorMap;

    public OftenBreakingCar(List<Repair> list) {
        super(list);
    }

    @Override
    public void execute() {
        List<Machine> machineList = new ArrayList();
        for (Repair repiar : list) {
            machineList.add(repiar.getMachine());
        }

        Map<Machine, Integer> countBreaking = new IdentityHashMap<>();
        for (Machine machine : machineList) {
            countBreaking.put(machine, Collections.frequency(machineList, machine));
        }

        List<Map.Entry<Machine, Integer>> listEntry = new ArrayList<>(countBreaking.entrySet());
        Collections.sort(listEntry, comparatorMap);

        Map.Entry<Machine, Integer> maxBreaking = listEntry.get(0);
        List<Map.Entry<Machine, Integer>> listEntryCopy = new ArrayList<>();
        for (Map.Entry<Machine, Integer> entry : listEntry) {
            if (entry.getValue().equals(maxBreaking.getValue())) {
                listEntryCopy.add(entry);
            }
        }

        printCollection(listEntryCopy, toStringFunc);
    }

    public Comparator<Map.Entry<?, ? extends Number>> getComparatorMap() {
        return comparatorMap;
    }

    public void setComparatorMap(Comparator<Map.Entry<?, ? extends Number>> comparatorMap) {
        this.comparatorMap = comparatorMap;
    }


    {
        setTitle("Самый ломающийся автомобиль");
        setBasement("Конец отчета\n");
        setComparatorMap(ReportsFunction.COMPARE_MAP_BY_VALUE);
        setToStringFunc(ReportsFunction.TO_STRING_OFTEN_BREAKING_CAR);
    }

}
