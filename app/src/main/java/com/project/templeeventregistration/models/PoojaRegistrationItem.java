package com.project.templeeventregistration.models;

public class PoojaRegistrationItem {
    private String name;
    private String date;
    private String userName;
    private String userPhone;
    private String id;

    public PoojaRegistrationItem(String name, String date, String userName, String userPhone) {
        this.name = name;
        this.date = date;
        this.userName = userName;
        this.userPhone = userPhone;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "id = " + id +
                "Name = " + name +
                "Date = " + date +
                "UserName = " + userName +
                "UserPhone = " + userPhone;
    }
}
