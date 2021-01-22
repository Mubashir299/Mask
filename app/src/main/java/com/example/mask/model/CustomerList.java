package com.example.mask.model;

public class CustomerList {
    private String userID;
    private String cnicNumber;
    private String maskNumber;

    public CustomerList() {

    }

    public CustomerList(String userID, String cnicNumber, String maskNumber) {
        this.userID = userID;
        this.cnicNumber = cnicNumber;
        this.maskNumber = maskNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCnicNumber() {
        return cnicNumber;
    }

    public void setCnicNumber(String cnicNumber) {
        this.cnicNumber = cnicNumber;
    }

    public String getMaskNumber() {
        return maskNumber;
    }

    public void setMaskNumber(String maskNumber) {
        this.maskNumber = maskNumber;
    }
}
