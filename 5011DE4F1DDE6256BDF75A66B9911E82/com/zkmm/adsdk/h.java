package com.zkmm.adsdk;

import android.app.Activity;

// compiled from: SourceFile
final class h implements Runnable {
    final /* synthetic */ E a;
    private final /* synthetic */ String b;

    h(E r1_E, String r2_String) {
        this.a = r1_E;
        this.b = r2_String;
    }

    public final void run() {
        s.a(this.b, (Activity) E.a(this.a).getContext());
        try {
            E.a(this.a).post(new i(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}