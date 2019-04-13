package com.example.adabooazeem.swift;

public class Data
{
    private ZoneOrders[] zoneOrders;

    private String zone_fee;

    private String zoneType;

    private String id;

    private String landMark;

    public ZoneOrders[] getZoneOrders ()
    {
        return zoneOrders;
    }

    public void setZoneOrders (ZoneOrders[] zoneOrders)
    {
        this.zoneOrders = zoneOrders;
    }

    public String getZone_fee ()
    {
        return zone_fee;
    }

    public void setZone_fee (String zone_fee)
    {
        this.zone_fee = zone_fee;
    }

    public String getZoneType ()
    {
        return zoneType;
    }

    public void setZoneType (String zoneType)
    {
        this.zoneType = zoneType;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getLandMark ()
    {
        return landMark;
    }

    public void setLandMark (String landMark)
    {
        this.landMark = landMark;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [zoneOrders = "+zoneOrders+", zone_fee = "+zone_fee+", zoneType = "+zoneType+", id = "+id+", landMark = "+landMark+"]";
    }
}