package com.zkmm.adsdk;

import android.content.Context;

// compiled from: SourceFile
final class x implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ Context b;

    x(w r1_w, String r2_String, Context r3_Context) {
        this.a = r2_String;
        this.b = r3_Context;
    }

    public final void run() {
        s.a(this.a, this.b, true, -1, false, this.b.getPackageName(), (short) 1);
    }
}