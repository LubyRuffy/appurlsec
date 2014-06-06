package com.google.analytics.tracking.android;

import com.google.analytics.tracking.android.AnalyticsThread.ClientIdCallback;

// compiled from: GoogleAnalytics.java
class v implements ClientIdCallback {
    final /* synthetic */ GoogleAnalytics a;

    v(GoogleAnalytics r1_GoogleAnalytics) {
        this.a = r1_GoogleAnalytics;
    }

    public void reportClientId(String r2_String) {
        this.a.f = r2_String;
    }
}