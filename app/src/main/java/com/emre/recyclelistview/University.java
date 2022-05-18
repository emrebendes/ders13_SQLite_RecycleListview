package com.emre.recyclelistview;

import java.io.Serializable;

public class University implements Serializable {
    private String name;
    private String city;
    private int logo;

    public University(String name, String city, int logo) {
        this.name = name;
        this.city = city;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getLogo() {
        return logo;
    }
}
