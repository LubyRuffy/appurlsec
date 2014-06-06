package com.zkmm.adsdk;

import android.app.Activity;

// compiled from: SourceFile
final class cq implements Runnable {
    final /* synthetic */ q a;
    private final /* synthetic */ String b;

    cq(q r1_q, String r2_String) {
        this.a = r1_q;
        this.b = r2_String;
    }

    public final void run() {
        s.a(this.b, (Activity) AdDisplayer.m);
        try {
            q.a(this.a).h.post(new cr(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}