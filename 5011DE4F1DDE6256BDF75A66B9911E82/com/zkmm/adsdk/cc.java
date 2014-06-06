package com.zkmm.adsdk;

import android.app.Activity;
import com.tencent.mm.sdk.platformtools.Util;

// compiled from: SourceFile
final class cc implements Runnable {
    private /* synthetic */ AdDisplayer a;
    private final /* synthetic */ Activity b;

    cc(AdDisplayer r1_AdDisplayer, Activity r2_Activity) {
        this.a = r1_AdDisplayer;
        this.b = r2_Activity;
    }

    public final void run() {
        this.a.p = this.b.getRequestedOrientation();
        this.b.setRequestedOrientation(this.a.q);
        this.b.getWindow().setFlags(Util.BYTE_OF_KB, Util.BYTE_OF_KB);
    }
}