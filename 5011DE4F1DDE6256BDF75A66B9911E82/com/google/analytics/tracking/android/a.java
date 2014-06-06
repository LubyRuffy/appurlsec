package com.google.analytics.tracking.android;

// compiled from: AdHitIdGenerator.java
class a {
    private boolean a;

    a() {
        try {
            this.a = Class.forName("com.google.ads.AdRequest") != null;
        } catch (ClassNotFoundException e) {
            this.a = false;
        }
    }

    int a_() {
        return this.a ? b.a().b() : 0;
    }
}