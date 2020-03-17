package com.example.codeinandroid;

public class Data {
    private String title;
    private String headerFlag;

    public Data(String title, String headerFlag) {
        this.title = title;
        this.headerFlag = headerFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String isHeadFlag() {
        return headerFlag;
    }

    public void setHeadFlag(String headerFlag) {
        this.headerFlag = headerFlag;
    }
}
