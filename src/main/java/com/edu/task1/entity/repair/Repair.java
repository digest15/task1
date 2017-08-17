package com.edu.task1.entity.repair;

import com.edu.task1.entity.carservice.CarService;
import com.edu.task1.entity.machine.Machine;
import com.edu.task1.entity.mechanic.Mechanic;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by damager on 17.08.17.
 */
public class Repair {
    private Date dateTime;
    private CarService carServise;
    private Machine machine;
    private BigDecimal amount;
    private Mechanic mechanic;
}
