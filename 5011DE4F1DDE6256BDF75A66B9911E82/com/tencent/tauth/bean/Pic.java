package com.tencent.tauth.bean;

// compiled from: ProGuard
public class Pic {
    private String a;
    private String b;
    private String c;
    private int d;
    private int e;

    public Pic(String r1_String, String r2_String, String r3_String, int r4i, int r5i) {
        this.a = r1_String;
        this.b = r2_String;
        this.c = r3_String;
        this.d = r4i;
        this.e = r5i;
    }

    public String getAlbumId() {
        return this.a;
    }

    public int getHeight() {
        return this.e;
    }

    public String getLloc() {
        return this.b;
    }

    public String getSloc() {
        return this.c;
    }

    public int getWidth() {
        return this.d;
    }

    public void setAlbumId(String r1_String) {
        this.a = r1_String;
    }

    public void setHeight(int r1i) {
        this.e = r1i;
    }

    public void setLloc(String r1_String) {
        this.b = r1_String;
    }

    public void setSloc(String r1_String) {
        this.c = r1_String;
    }

    public void setWidth(int r1i) {
        this.d = r1i;
    }

    public String toString() {
        return "albumid :" + this.a + "\nlloc: " + this.b + "\nsloc: " + this.c + "\nheight: " + this.e + "\nwidth: " + this.d + "\n";
    }
}