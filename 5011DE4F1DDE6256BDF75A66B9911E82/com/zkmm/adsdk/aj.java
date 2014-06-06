package com.zkmm.adsdk;

import android.os.Vibrator;

// compiled from: SourceFile
final class aj implements Runnable {
    private final /* synthetic */ Vibrator a;

    aj(aD r1_aD, Vibrator r2_Vibrator) {
        this.a = r2_Vibrator;
    }

    public final void run() {
        if (this.a != null) {
            this.a.cancel();
        }
        cs.a = false;
    }
}