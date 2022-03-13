package com.example.msm.ui.UI_Customer_Visitor.Change_Speed;

import java.io.Serializable;

public class CS_Custom_Recycler_Item implements Serializable {
    private String Text;
    private String Speed_Titel;

    public CS_Custom_Recycler_Item(String text, String speedTitel) {
        Text = text;
        Speed_Titel = speedTitel;
    }

    public String getText() {
        return Text;
    }

    public String getSpeed_Titel() {
        return Speed_Titel;
    }
}
