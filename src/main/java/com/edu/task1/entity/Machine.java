package com.edu.task1.entity;

import java.util.Date;

public abstract class Machine {
    private String namePicking;
    private String vin;
    private Date releaseYear;
    private Color color;
    private Mark mark;

    public String getNamePicking() {
        return namePicking;
    }

    public void setNamePicking(String namePicking) {
        this.namePicking = namePicking;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
