package com.example.msm.FirebaseMSMTest.Modles;

public class Gates {

    private String area_Name;
    private String area_Code;
    boolean is_Available;

    public Gates() {
    }
    public Gates(String area_Name, String area_Code , boolean is_Available) {
        this.area_Name = area_Name;
        this.area_Code = area_Code;
        this.is_Available = is_Available;
    }

    public String getArea_Name() {
        return area_Name;
    }

    public void setArea_Name(String area_Name) {
        this.area_Name = area_Name;
    }

    public String getArea_Code() {
        return area_Code;
    }

    public void setArea_Code(String area_Code) {
        this.area_Code = area_Code;
    }

    public boolean getIs_Available() {
        return is_Available;
    }

    public void setIs_Available(boolean is_Available) {
        this.is_Available = is_Available;
    }
}
