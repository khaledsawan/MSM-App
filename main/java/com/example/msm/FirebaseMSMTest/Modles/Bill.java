package com.example.msm.FirebaseMSMTest.Modles;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Bill implements Serializable {
    private String serial_Number;
    private String date;
    private String invoice_Value;
    private String e_Mail;

    public Bill() {
    }

    public Bill( String invoice_Value, String e_Mail) {
        this.invoice_Value = invoice_Value;
        this.e_Mail = e_Mail;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = new Date();
        this.date = formatter.format(date);
    }

    public String getSerial_Number() {
        return serial_Number;
    }

    public void setSerial_Number(String serial_Number) {
        this.serial_Number = serial_Number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInvoice_Value() {
        return invoice_Value;
    }

    public void setInvoice_Value(String invoice_Value) {
        this.invoice_Value = invoice_Value;
    }

    public String getE_Mail() {
        return e_Mail;
    }

    public void setE_Mail(String e_Mail) {
        this.e_Mail = e_Mail;
    }
}

