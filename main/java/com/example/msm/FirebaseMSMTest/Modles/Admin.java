package com.example.msm.FirebaseMSMTest.Modles;

public class Admin {
    private String e_Mail;
    private String password;

    public Admin() {
    }

    public Admin(String e_Mail, String password) {
        this.e_Mail = e_Mail;
        this.password = password;
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
}
