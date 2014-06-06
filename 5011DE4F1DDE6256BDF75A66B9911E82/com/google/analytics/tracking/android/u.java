package com.google.analytics.tracking.android;

import com.google.analytics.tracking.android.Analytics.AppOptOutCallback;

// compiled from: GoogleAnalytics.java
class u implements AppOptOutCallback {
    final /* synthetic */ GoogleAnalytics a;

    u(GoogleAnalytics r1_GoogleAnalytics) {
        this.a = r1_GoogleAnalytics;
    }

    public void reportAppOptOut(boolean r3z) {
        this.a.g = Boolean.valueOf(r3z);
    }
}