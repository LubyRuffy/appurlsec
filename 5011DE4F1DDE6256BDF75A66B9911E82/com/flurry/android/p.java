package com.flurry.android;

import android.content.Context;

// compiled from: SourceFile
final class p implements Runnable {
    private /* synthetic */ Context a;
    private /* synthetic */ boolean b;
    private /* synthetic */ FlurryAgent c;

    p(FlurryAgent r1_FlurryAgent, Context r2_Context, boolean r3z) {
        this.c = r1_FlurryAgent;
        this.a = r2_Context;
        this.b = r3z;
    }

    public final void run() {
        if (!FlurryAgent.a(this.c)) {
            FlurryAgent.a(this.c, this.a);
        }
        FlurryAgent.a(this.c, this.a, this.b);
    }
}