package com.project.templeeventregistration.models;

public class PoojaRegistrationAdminItem {
    private String poojaName;
    private String poojaDate;
    private String poojaPrice;
    private String userName;
    private String userPhone;
    private String paymentId;

    public PoojaRegistrationAdminItem(String poojaName, String poojaDate, String userName, String userPhone) {
        this.poojaName = poojaName;
        this.poojaDate = poojaDate;
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public PoojaRegistrationAdminItem(){

    }

    public PoojaRegistrationAdminItem(String paymentId, String poojaName, String poojaDate, String poojaPrice, String userName, String userPhone) {
        this.paymentId = paymentId;
        this.poojaName = poojaName;
        this.poojaDate = poojaDate;
        this.poojaPrice = poojaPrice;
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public String getPoojaName() {
        return poojaName;
    }

    public void setPoojaName(String poojaName) {
        this.poojaName = poojaName;
    }

    public String getPoojaDate() {
        return poojaDate;
    }

    public void setPoojaDate(String poojaDate) {
        this.poojaDate = poojaDate;
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return  "id = " + paymentId +
                "Name = " + poojaName +
                "Date = " + poojaDate +
                "UserName = " + userName +
                "UserPhone = " + userPhone;
    }
}
