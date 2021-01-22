package com.example.mask.model;

public class Customer {
    private String customerCnic;
    private String maskNumber;
    private String  id;

    public Customer() {
    }

    public Customer(String customerCnic, String maskNumber) {
        this.customerCnic = customerCnic;
        this.maskNumber = maskNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerCnic() {
        return customerCnic;
    }

    public void setCustomerCnic(String customerCnic) {
        this.customerCnic = customerCnic;
    }

    public String getMaskNumber() {
        return maskNumber;
    }

    public void setMaskNumber(String maskNumber) {
        this.maskNumber = maskNumber;
    }
}
