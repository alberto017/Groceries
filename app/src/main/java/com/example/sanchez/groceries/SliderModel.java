package com.example.sanchez.groceries;

public class SliderModel {

    private int banner;
    private String backGroundColor;

    public SliderModel(int banner, String backGroundColor) {
        this.banner = banner;
        this.backGroundColor = backGroundColor;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    public String getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }
}
