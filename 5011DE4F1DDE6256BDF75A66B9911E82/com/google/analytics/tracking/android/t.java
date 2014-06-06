package com.google.analytics.tracking.android;

import com.google.analytics.tracking.android.AnalyticsThread.ClientIdCallback;

// compiled from: GAThread.java
class t implements Runnable {
    final /* synthetic */ ClientIdCallback a;
    final /* synthetic */ o b;

    t(o r1_o, ClientIdCallback r2_ClientIdCallback) {
        this.b = r1_o;
        this.a = r2_ClientIdCallback;
    }

    public void run() {
        this.a.reportClientId(this.b.h);
    }
}