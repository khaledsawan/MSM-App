package com.example.msm.FirebaseMSMTest.Modles;

import java.io.Serializable;

public class Center implements Serializable {
    private String e_Mail;
    private String password;
    private String location;

    public Center() {
    }

    public Center(String e_Mail, String password, String location) {
        this.e_Mail = e_Mail;
        this.password = password;
        this.location = location;
    }

    public String getE_Mail() {
        return e_Mail;
    }

    public void setE_Mail(String e_Mail) {
        this.e_Mail = e_Mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
