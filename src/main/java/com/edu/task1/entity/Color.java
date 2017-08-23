package com.edu.task1.entity;

import java.io.Serializable;

public class Color implements Serializable {
    private String rgb;
    private String name;

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
