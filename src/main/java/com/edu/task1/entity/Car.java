package com.edu.task1.entity;

import java.util.List;

public class Car extends Machine {
    public List<String> strings;
    public List<Color> colors;
    public int[] ints = new int[5];

    private int numberPassengerSeats;

    public int getNumberPassengerSeats() {
        return numberPassengerSeats;
    }

    public void setNumberPassengerSeats(int numberPassengerSeats) {
        this.numberPassengerSeats = numberPassengerSeats;
    }

}
