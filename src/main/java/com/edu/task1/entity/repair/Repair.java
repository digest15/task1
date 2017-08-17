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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public CarService getCarServise() {
        return carServise;
    }

    public void setCarServise(CarService carServise) {
        this.carServise = carServise;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }
}
