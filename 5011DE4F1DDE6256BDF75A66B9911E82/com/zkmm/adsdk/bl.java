package com.zkmm.adsdk;

import android.view.View;

// compiled from: SourceFile
final class bl implements Runnable {
    private /* synthetic */ bj a;
    private final /* synthetic */ View b;
    private final /* synthetic */ int c;
    private final /* synthetic */ int d;
    private final /* synthetic */ int e;
    private final /* synthetic */ int f;

    bl(bj r1_bj, View r2_View, int r3i, int r4i, int r5i, int r6i) {
        this.a = r1_bj;
        this.b = r2_View;
        this.c = r3i;
        this.d = r4i;
        this.e = r5i;
        this.f = r6i;
    }

    public final void run() {
        this.a.a.showAtLocation(this.b, 0, this.c, this.d);
        this.a.a.update(this.c, this.d + this.a.k, this.e, this.f - this.a.k, true);
    }
}