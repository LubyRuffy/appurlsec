package com.crashlytics.android;

import com.crashlytics.android.internal.v;

// compiled from: SourceFile
final class g implements Runnable {
    private /* synthetic */ Runnable a;

    g(ba r1_ba, Runnable r2_Runnable) {
        this.a = r2_Runnable;
    }

    public final void run() {
        try {
            this.a.run();
        } catch (Exception e) {
            v.a().b().a(Crashlytics.TAG, "Failed to execute task.", e);
        }
    }
}