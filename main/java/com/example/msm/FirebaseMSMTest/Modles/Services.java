package com.example.msm.FirebaseMSMTest.Modles;

import java.io.Serializable;

public class Services implements Serializable {
    private String serial_Number;
    private String title;
    private String subject;
    private String cost;


    public Services() {
    }


    public Services( String title, String subject, String cost) {
        this.title = title;
        this.subject = subject;
        this.cost = cost;
    }

    public String getSerial_Number() {
        return serial_Number;
    }

    public void setSerial_Number(String serial_Number) {
        this.serial_Number = serial_Number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
