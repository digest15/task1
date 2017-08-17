package com.edu.task1.entity.machine;

public class Car extends Machine {
    private int numberPassengerSeats;
    private int numberPassengerStanding;

    public int getNumberPassengerSeats() {
        return numberPassengerSeats;
    }

    public void setNumberPassengerSeats(int numberPassengerSeats) {
        this.numberPassengerSeats = numberPassengerSeats;
    }

    public int getNumberPassengerStanding() {
        return numberPassengerStanding;
    }

    public void setNumberPassengerStanding(int numberPassengerStanding) {
        this.numberPassengerStanding = numberPassengerStanding;
    }
}
