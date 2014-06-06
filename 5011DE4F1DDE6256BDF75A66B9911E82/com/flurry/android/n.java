package com.flurry.android;

import android.content.Context;

// compiled from: SourceFile
final class n implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ FlurryAgent b;
    private /* synthetic */ boolean c;

    n(FlurryAgent r1_FlurryAgent, boolean r2z, Context r3_Context) {
        this.b = r1_FlurryAgent;
        this.c = r2z;
        this.a = r3_Context;
    }

    public final void run() {
        FlurryAgent.b(this.b);
        FlurryAgent.c(this.b);
        if (!this.c) {
            FlurryAgent.d(this.b).postDelayed(new x(this), FlurryAgent.h());
        }
        if (FlurryAgent.i()) {
            FlurryAgent.e(this.b).d();
        }
    }
}