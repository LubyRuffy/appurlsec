package com.tencent.tauth.bean;

// compiled from: ProGuard
public class UserInfo {
    private String a;
    private String b;
    private String c;
    private String d;

    public UserInfo(String r1_String, String r2_String, String r3_String, String r4_String) {
        this.a = r1_String;
        this.b = r2_String;
        this.c = r3_String;
        this.d = r4_String;
    }

    public String getIcon_100() {
        return this.d;
    }

    public String getIcon_30() {
        return this.b;
    }

    public String getIcon_50() {
        return this.c;
    }

    public String getNickName() {
        return this.a;
    }

    public void setIcon_100(String r1_String) {
        this.d = r1_String;
    }

    public void setIcon_30(String r1_String) {
        this.b = r1_String;
    }

    public void setIcon_50(String r1_String) {
        this.c = r1_String;
    }

    public void setNickName(String r1_String) {
        this.a = r1_String;
    }

    public String toString() {
        return "nickname: " + this.a + "\nicon_30: " + this.b + "\nicon_50: " + this.c + "\nicon_100: " + this.d + "\n";
    }
}