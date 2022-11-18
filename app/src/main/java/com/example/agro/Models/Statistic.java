package com.example.agro.Models;

public class Statistic {
    public String id;
    public String date;
    public double body_temperature,heartrate,humidity;
    public int spo2, rs, temperature;

    public Statistic(double ts, double hs, double sms, int gss, int rs, int fs,String date) {
        this.body_temperature = ts;
        this.heartrate = hs;
        this.humidity = sms;
        this.spo2 = gss;
        this.rs = rs;
        this.temperature = fs;
        this.date=date;
    }

    public Statistic(String id, double ts, double hs, double sms, int gss, int rs, int fs,String date) {
        this.id = id;
        this.body_temperature = ts;
        this.heartrate = hs;
        this.humidity = sms;
        this.spo2 = gss;
        this.rs = rs;
        this.temperature = fs;
        this.date=date;
    }

    public Statistic() {
    }

    public String getId() {
        return id;
    }

    public double getTs() {
        return body_temperature;
    }

    public double getHs() {
        return heartrate;
    }

    public double getSms() {
        return humidity;
    }

    public int getGss() {
        return spo2;
    }

    public int getRs() {
        return rs;
    }

    public int getFs() {
        return temperature;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTs(double ts) {
        this.body_temperature = ts;
    }

    public void setHs(double hs) {
        this.heartrate = hs;
    }

    public void setSms(double sms) {
        this.humidity = sms;
    }

    public void setGss(int gss) {
        this.spo2 = gss;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public void setFs(int fs) {
        this.temperature = fs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
