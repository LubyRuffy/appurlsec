package com.google.analytics.tracking.android;

import com.google.analytics.tracking.android.Analytics.AppOptOutCallback;

// compiled from: GAThread.java
class s implements Runnable {
    final /* synthetic */ AppOptOutCallback a;
    final /* synthetic */ o b;

    s(o r1_o, AppOptOutCallback r2_AppOptOutCallback) {
        this.b = r1_o;
        this.a = r2_AppOptOutCallback;
    }

    public void run() {
        this.a.reportAppOptOut(this.b.d);
    }
}