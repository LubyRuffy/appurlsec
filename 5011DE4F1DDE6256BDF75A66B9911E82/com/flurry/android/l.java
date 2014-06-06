package com.flurry.android;

import android.content.Context;
import android.os.Handler;

// compiled from: SourceFile
final class l implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ ab c;
    final /* synthetic */ ag d;

    l(ag r1_ag, String r2_String, Context r3_Context, ab r4_ab) {
        this.d = r1_ag;
        this.a = r2_String;
        this.b = r3_Context;
        this.c = r4_ab;
    }

    public final void run() {
        new Handler().post(new y(this, this.d.d(this.a)));
    }
}