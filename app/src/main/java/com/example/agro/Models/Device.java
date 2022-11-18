package com.example.agro.Models;

public class Device {
    public String id;
    public String code;
    public String user;
    public int status;
    public String datakey;

    public Device() {
    }

    public Device(String code,String user,int status,String datakey) {
        this.code = code;
        this.user=user;
        this.status=status;
        this.datakey=datakey;
    }

    public Device(String id, String code,String user,int status,String datakey) {
        this.id = id;
        this.code = code;
        this.user=user;
        this.status=status;
        this.datakey=datakey;
    }
}
