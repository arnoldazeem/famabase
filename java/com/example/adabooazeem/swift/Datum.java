package com.example.adabooazeem.swift;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("pickupZoneName")
    @Expose
    private String pickupZoneName;
    @SerializedName("zoneType")
    @Expose
    private String zoneType;
    @SerializedName("zone_fee")
    @Expose
    private String zoneFee;
    @SerializedName("zoneOrders")
    @Expose
    private List<ZoneOrders> zoneOrders = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Datum() {
    }

    /**
     *
     * @param id
     * @param zoneType
     * @param pickupZoneName
     * @param zoneFee
     * @param zoneOrders
     */
    public Datum(String id, String pickupZoneName, String zoneType, String zoneFee, List<ZoneOrders> zoneOrders) {
        super();
        this.id = id;
        this.pickupZoneName = pickupZoneName;
        this.zoneType = zoneType;
        this.zoneFee = zoneFee;
        this.zoneOrders = zoneOrders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpickupZoneName() {
        return pickupZoneName;
    }

    public void setpickupZoneName(String landMark) {
        this.pickupZoneName = landMark;
    }

    public String getZoneType() {
        return zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
    }

    public String getZoneFee() {
        return zoneFee;
    }

    public void setZoneFee(String zoneFee) {
        this.zoneFee = zoneFee;
    }

    public List<ZoneOrders> getZoneOrders() {
        return zoneOrders;
    }

    public void setZoneOrders(ArrayList<ZoneOrders> zoneOrders) {
        this.zoneOrders = zoneOrders;
    }

}