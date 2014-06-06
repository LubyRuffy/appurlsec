package com.tencent.tauth.bean;

// compiled from: ProGuard
public class Album {
    private String a;
    private int b;
    private long c;
    private String d;
    private String e;
    private long f;
    private int g;

    public Album(String r1_String, int r2i, long r3j, String r5_String, String r6_String, long r7j, int r9i) {
        this.a = r1_String;
        this.b = r2i;
        this.c = r3j;
        this.d = r5_String;
        this.e = r6_String;
        this.f = r7j;
        this.g = r9i;
    }

    public String getAlbumid() {
        return this.a;
    }

    public int getClassid() {
        return this.b;
    }

    public long getCreatetime() {
        return this.c;
    }

    public String getDesc() {
        return this.d;
    }

    public String getName() {
        return this.e;
    }

    public long getPicnum() {
        return this.f;
    }

    public int getPriv() {
        return this.g;
    }

    public void setAlbumid(String r1_String) {
        this.a = r1_String;
    }

    public void setClassid(int r1i) {
        this.b = r1i;
    }

    public void setCreatetime(long r1j) {
        this.c = r1j;
    }

    public void setDesc(String r1_String) {
        this.d = r1_String;
    }

    public void setName(String r1_String) {
        this.e = r1_String;
    }

    public void setPicnum(long r1j) {
        this.f = r1j;
    }

    public void setPriv(int r1i) {
        this.g = r1i;
    }

    public String toString() {
        return "albumid: " + this.a + "\nclassid: " + this.b + "\ncreatetime: " + this.c + "\ndesc: " + this.d + "\nname: " + this.e + "\npicnum: " + this.f + "\npriv: " + this.g + "\n";
    }
}