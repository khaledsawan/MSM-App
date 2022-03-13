package com.example.msm.FirebaseMSMTest.Modles;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Request implements Serializable {

    private String serial_Number;
    private String from;
    private String to;
    private String message;
    private String type;
    private String e_Mail;
    private String date;
    SharedPreferences sharedPreferences;
    Context context;

    public Request() {
    }

    public Request(String from, String to, String message, String type,Context context) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.type = type;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LogIN", Context.MODE_PRIVATE);

        this.e_Mail = sharedPreferences.getString("e_Mail", "Is not logged in");

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getE_Mail() {
        return e_Mail;
    }

    public void setE_Mail(String e_Mail) {
        this.e_Mail = e_Mail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
