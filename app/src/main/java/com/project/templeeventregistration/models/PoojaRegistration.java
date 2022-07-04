package com.project.templeeventregistration.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class PoojaRegistration implements Serializable {
    @Exclude
    private String id;
    private String name;
    private String date;
    private String price;

    PoojaRegistration(){}

    public PoojaRegistration(String name, String date, String price) {
        this.name = name;
        this.date = date;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
