package com.example.pojet_app_mobile;


public class DataClass {


    private String dataName;
    private String dataEmail;
    private String datatel;
    private String datamdp;


    private String datacrdt;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getDataName() {
        return dataName;
    }

    public String getDataEmail() {
        return dataEmail;
    }

    public String getDatatel() {
        return datatel;
    }

    public String getDatamdp() {
        return datamdp;
    }

    public String getDatacrdt() {
        return datacrdt;
    }


    public DataClass(String dataName, String dataEmail, String datatel, String datamdp, String datacrdt) {
        this.dataName = dataName;
        this.dataEmail = dataEmail;
        this.datatel = datatel;
        this.datamdp = datamdp;
        this.datacrdt = datacrdt;
    }

    public DataClass(String dataName, String datatel, String datacrdt) {
        this.dataName = dataName;
        this.datatel = datatel;
        this.datacrdt = datacrdt;
    }

    public DataClass(){

    }
}