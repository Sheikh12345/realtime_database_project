package com.tasktakers.realtimedatabase;

public class Donor {
    private String fullName;
    private String email;
    private String city;
    private String bloodGroup;
    public Donor(){}

    public Donor(String fullName, String email, String bloodGroup, String city) {
        this.fullName = fullName;
        this.city = city;
        this.email = email;
        this.bloodGroup = bloodGroup;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
