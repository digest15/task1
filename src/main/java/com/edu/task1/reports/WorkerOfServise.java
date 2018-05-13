package com.edu.task1.reports;

import com.edu.task1.entity.CarService;
import com.edu.task1.entity.Mechanic;
import com.edu.task1.entity.Repair;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;

public class WorkerOfServise extends AbstractReport<Repair> {

    public WorkerOfServise(List<Repair> list) {
        super(list);
    }

    @Override
    public void execute() {
        Multimap<String, Mechanic> map = HashMultimap.create();
        for (Repair repair : list) {
            Mechanic mechanic = repair.getMechanic();
            CarService service = repair.getCarServise();
            map.put(service.getName(), mechanic);
        }

        for (String servise : map.keySet()) {
            body += servise;
            Collection<Mechanic> mechanics = map.get(servise);
            List<Mechanic> mechanicsList = Lists.newArrayList(mechanics);
            printCollection(mechanicsList, toStringFunc);
        }
    }


    {
        setTitle("Автосервис и его сотрудники");
        setBasement("Конец отчета\n");
        setToStringFunc(ReportsFunction.TO_STRING_MECHANIC);
    }
}
