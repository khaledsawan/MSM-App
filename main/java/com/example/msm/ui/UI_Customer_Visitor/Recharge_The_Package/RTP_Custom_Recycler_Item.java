package com.example.msm.ui.UI_Customer_Visitor.Recharge_The_Package;

import java.io.Serializable;

public class RTP_Custom_Recycler_Item implements Serializable {
    private String Size;
    private String Price;
    private String OK;

    public RTP_Custom_Recycler_Item(String size, String price, String OK) {
        Size = size;
        Price = price;
        this.OK = OK;
    }

    public String getSize() {
        return Size;
    }

    public String getPrice() {
        return Price;
    }

    public String getOK() {
        return OK;
    }
}
