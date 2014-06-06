package com.zkmm.adsdk;

import android.os.Vibrator;

// compiled from: SourceFile
final class ck implements Runnable {
    private final /* synthetic */ Vibrator a;

    ck(q r1_q, Vibrator r2_Vibrator) {
        this.a = r2_Vibrator;
    }

    public final void run() {
        if (this.a != null) {
            this.a.cancel();
        }
        cs.a = false;
    }
}