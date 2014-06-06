package com.google.analytics.tracking.android;

import java.util.Random;

// compiled from: AdMobInfo.java
class b {
    private static final b a;
    private int b;
    private Random c;

    static {
        a = new b();
    }

    private b() {
        this.c = new Random();
    }

    static b a() {
        return a;
    }

    int b_() {
        this.b = this.c.nextInt(2147483646) + 1;
        return this.b;
    }
}