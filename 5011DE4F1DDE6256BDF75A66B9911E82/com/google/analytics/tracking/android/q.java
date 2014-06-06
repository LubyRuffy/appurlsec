package com.google.analytics.tracking.android;

// compiled from: GAThread.java
class q implements Runnable {
    final /* synthetic */ o a;

    q(o r1_o) {
        this.a = r1_o;
    }

    public void run() {
        this.a.j.dispatch();
    }
}