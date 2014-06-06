package com.google.analytics.tracking.android;

import java.io.IOException;

// compiled from: GAThread.java
class r implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ o b;

    r(o r1_o, boolean r2z) {
        this.b = r1_o;
        this.a = r2z;
    }

    public void run() {
        if (this.b.d == this.a) {
        } else {
            if (this.a) {
                try {
                    this.b.k.getFileStreamPath("gaOptOut").createNewFile();
                } catch (IOException e) {
                    z.h("Error creating optOut file.");
                }
                this.b.j.clearHits();
            } else {
                this.b.k.deleteFile("gaOptOut");
            }
            this.b.d = this.a;
        }
    }
}