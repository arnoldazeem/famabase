package com.example.adabooazeem.swift;

public class DeliveryPojo {

    String name;
    String location;
    String Deliveryday;
    String gps;

    public DeliveryPojo(String name, String location, String deliveryday, String gps) {
        this.name = name;
        this.location = location;
        Deliveryday = deliveryday;
        this.gps = gps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeliveryday() {
        return Deliveryday;
    }

    public void setDeliveryday(String deliveryday) {
        Deliveryday = deliveryday;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }
}
