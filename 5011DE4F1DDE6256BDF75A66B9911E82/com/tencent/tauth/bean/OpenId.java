package com.tencent.tauth.bean;

// compiled from: ProGuard
public class OpenId {
    private String a;
    private String b;

    public OpenId(String r1_String, String r2_String) {
        this.a = r1_String;
        this.b = r2_String;
    }

    public String getClientId() {
        return this.b;
    }

    public String getOpenId() {
        return this.a;
    }

    public void setClientId(String r1_String) {
        this.b = r1_String;
    }

    public void setOpenId(String r1_String) {
        this.a = r1_String;
    }

    public String toString() {
        return this.a;
    }
}