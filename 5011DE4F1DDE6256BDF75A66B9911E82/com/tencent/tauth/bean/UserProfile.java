package com.tencent.tauth.bean;

// compiled from: ProGuard
public class UserProfile {
    private String a;
    private int b;
    private String c;
    private String d;
    private String e;

    public UserProfile(String r1_String, int r2i, String r3_String, String r4_String, String r5_String) {
        this.a = r1_String;
        this.b = r2i;
        this.c = r3_String;
        this.d = r4_String;
        this.e = r5_String;
    }

    public int getGender() {
        return this.b;
    }

    public String getIcon_100() {
        return this.e;
    }

    public String getIcon_30() {
        return this.c;
    }

    public String getIcon_50() {
        return this.d;
    }

    public String getRealName() {
        return this.a;
    }

    public void setGender(int r1i) {
        this.b = r1i;
    }

    public void setIcon_100(String r1_String) {
        this.e = r1_String;
    }

    public void setIcon_30(String r1_String) {
        this.c = r1_String;
    }

    public void setIcon_50(String r1_String) {
        this.d = r1_String;
    }

    public void setNickName(String r1_String) {
        this.a = r1_String;
    }

    public String toString() {
        return "realName: " + this.a + "\ngender: " + (this.b == 0 ? "\u5973" : "\u7537") + "\nicon_30: " + this.c + "\nicon_50: " + this.d + "\nicon_100: " + this.e + "\n";
    }
}