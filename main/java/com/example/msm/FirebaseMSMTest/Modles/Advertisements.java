package com.example.msm.FirebaseMSMTest.Modles;

import java.io.Serializable;

public class Advertisements implements Serializable {
    private String serial_Number;
    private String title;
    private String subject;

    public Advertisements() {
    }

    public Advertisements( String title, String subject) {
        this.title = title;
        this.subject = subject;
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
}
