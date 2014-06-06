package com.zkmm.adsdk;

import android.app.Activity;

// compiled from: SourceFile
final class ap implements Runnable {
    final /* synthetic */ aD a;
    private final /* synthetic */ String b;

    ap(aD r1_aD, String r2_String) {
        this.a = r1_aD;
        this.b = r2_String;
    }

    public final void run() {
        s.a(this.b, (Activity) bj.j);
        try {
            aD.a(this.a).c.post(new ar(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}