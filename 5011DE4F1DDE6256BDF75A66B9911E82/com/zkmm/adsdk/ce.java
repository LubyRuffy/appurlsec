package com.zkmm.adsdk;

import android.view.View;

// compiled from: SourceFile
final class ce implements Runnable {
    private /* synthetic */ AdDisplayer a;
    private final /* synthetic */ View b;
    private final /* synthetic */ int c;
    private final /* synthetic */ int d;
    private final /* synthetic */ int e;
    private final /* synthetic */ int f;

    ce(AdDisplayer r1_AdDisplayer, View r2_View, int r3i, int r4i, int r5i, int r6i) {
        this.a = r1_AdDisplayer;
        this.b = r2_View;
        this.c = r3i;
        this.d = r4i;
        this.e = r5i;
        this.f = r6i;
    }

    public final void run() {
        this.a.e.showAtLocation(this.b, 0, this.c, this.d);
        this.a.e.update(this.c, this.d, this.e, this.f, true);
    }
}