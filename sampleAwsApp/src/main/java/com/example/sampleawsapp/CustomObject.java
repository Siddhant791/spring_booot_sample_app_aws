package com.example.sampleawsapp;


public class CustomObject {
    private String cool;
    private String warm;
    private String cold;

    @Override
    public String toString() {
        return "CustomObject{" +
                "cool='" + cool + '\'' +
                ", warm='" + warm + '\'' +
                ", cold='" + cold + '\'' +
                '}';
    }

    public String getCool() {
        return cool;
    }

    public void setCool(String cool) {
        this.cool = cool;
    }

    public String getWarm() {
        return warm;
    }

    public void setWarm(String warm) {
        this.warm = warm;
    }

    public String getCold() {
        return cold;
    }

    public void setCold(String cold) {
        this.cold = cold;
    }
}