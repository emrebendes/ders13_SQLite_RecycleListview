package com.emre.recyclelistview;

import android.graphics.Bitmap;

import java.io.Serializable;

public class University implements Serializable {
    private String name;
    private String city;
    private Bitmap logo;

    public University(String name, String city, Bitmap logo) {
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

    public Bitmap getLogo() {
        return logo;
    }
}
