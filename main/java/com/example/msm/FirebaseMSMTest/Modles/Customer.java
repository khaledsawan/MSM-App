package com.example.msm.FirebaseMSMTest.Modles;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Customer implements Serializable {
    private String e_Mail;
    private String fName;
    private String lName;
    private String password;
    private String gender;
    private String phone_Number;
    private int internet_Speed;
    private String join_Date;

    private Integer c_package;

    private boolean account_Status;

    private int invoice_Value;
    private ArrayList<Integer> services;
    private ArrayList<Integer> advertisement;

    public Customer() {
    }

    public Customer(String e_Mail, String f_Name, String l_Name, String password, String gender, String phone_Number, int internet_Speed, Integer c_package , boolean account_Status, int invoice_Value, ArrayList<Integer> services, ArrayList<Integer> advertisement) {
        this.e_Mail = e_Mail;
        fName = f_Name;
        lName = l_Name;
        this.password = password;
        this.gender = gender;
        this.phone_Number = phone_Number;
        this.internet_Speed = internet_Speed;

        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = new Date();
        this.join_Date = format.format(date);
        this.c_package = c_package;
        this.account_Status = account_Status;
        this.invoice_Value = invoice_Value;
        this.services = services;
        this.advertisement = advertisement;

    }

    public Integer getC_package() {
        return c_package;
    }

    public void setC_package(Integer c_package) {
        this.c_package = c_package;
    }

    public String getE_Mail() {
        return e_Mail;
    }

    public void setE_Mail(String e_Mail) {
        this.e_Mail = e_Mail;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public int getInternet_Speed() {
        return internet_Speed;
    }

    public void setInternet_Speed(int internet_Speed) {
        this.internet_Speed = internet_Speed;
    }

    public String getJoin_Date() {
        return join_Date;
    }

    public void setJoin_Date(String join_Date) {
        this.join_Date = join_Date;
    }

    public boolean isAccount_Status() {
        return account_Status;
    }

    public void setAccount_Status(boolean account_Status) {
        this.account_Status = account_Status;
    }

    public int getInvoice_Value() {
        return invoice_Value;
    }

    public void setInvoice_Value(int invoice_Value) {
        this.invoice_Value = invoice_Value;
    }

    public ArrayList<Integer> getServices() {
        return services;
    }

    public void setServices(ArrayList<Integer> services) {
        this.services = services;
    }

    public ArrayList<Integer> getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(ArrayList<Integer> advertisement) {
        this.advertisement = advertisement;
    }
}
