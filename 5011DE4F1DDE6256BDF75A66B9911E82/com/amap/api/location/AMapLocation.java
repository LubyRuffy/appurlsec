package com.amap.api.location;

import android.location.Location;

public class AMapLocation extends Location {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public AMapLocation(Location r1_Location) {
        super(r1_Location);
    }

    public AMapLocation(String r1_String) {
        super(r1_String);
    }

    public String getAdCode() {
        return this.e;
    }

    public String getCity() {
        return this.b;
    }

    public String getCityCode() {
        return this.d;
    }

    public String getDistrict() {
        return this.c;
    }

    public String getFloor() {
        return this.g;
    }

    public String getPoiId() {
        return this.f;
    }

    public String getProvince() {
        return this.a;
    }

    public void setAdCode(String r1_String) {
        this.e = r1_String;
    }

    public void setCity(String r1_String) {
        this.b = r1_String;
    }

    public void setCityCode(String r1_String) {
        this.d = r1_String;
    }

    public void setDistrict(String r1_String) {
        this.c = r1_String;
    }

    public void setFloor(String r1_String) {
        this.g = r1_String;
    }

    public void setPoiId(String r1_String) {
        this.f = r1_String;
    }

    public void setProvince(String r1_String) {
        this.a = r1_String;
    }
}