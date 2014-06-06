package com.zkmm.adsdk;

import android.app.Activity;
import com.tencent.mm.sdk.platformtools.Util;

// compiled from: SourceFile
final class bx implements Runnable {
    private /* synthetic */ bw a;
    private final /* synthetic */ Activity b;

    bx(bw r1_bw, Activity r2_Activity) {
        this.a = r1_bw;
        this.b = r2_Activity;
    }

    public final void run() {
        this.b.setRequestedOrientation(bw.a(this.a).p);
        this.b.getWindow().clearFlags(Util.BYTE_OF_KB);
    }
}