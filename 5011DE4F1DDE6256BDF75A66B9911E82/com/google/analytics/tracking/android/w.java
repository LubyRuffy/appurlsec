package com.google.analytics.tracking.android;

// compiled from: Hit.java
class w {
    private String a;
    private final long b;
    private final long c;
    private String d;

    w(String r1_String, long r2j, long r4j) {
        this.a = r1_String;
        this.b = r2j;
        this.c = r4j;
    }

    String a() {
        return this.a;
    }

    void a(String r1_String) {
        this.a = r1_String;
    }

    long b() {
        return this.b;
    }

    long c() {
        return this.c;
    }

    public String getHitUrl() {
        return this.d;
    }

    public void setHitUrl(String r1_String) {
        this.d = r1_String;
    }
}