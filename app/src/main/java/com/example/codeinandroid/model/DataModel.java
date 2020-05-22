package com.example.codeinandroid.model;

public class DataModel {
    private String type;
    private String data;
    private String column1;
    private String column2;
    private String column3;
    private int image;

    public DataModel(String type, String column1, String column2, String column3) {
        this.type = type;
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
    }

    public DataModel(String type, String title) {
        this.data = title;
        this.type = type;
    }

    public DataModel(String type, int image) {
        this.image = image;
        this.type = type;
    }

    public String getColumn1() {
        return column1;
    }

    public String getColumn2() {
        return column2;
    }

    public String getColumn3() {
        return column3;
    }

    public int getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
