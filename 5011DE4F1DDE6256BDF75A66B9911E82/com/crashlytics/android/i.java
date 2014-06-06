package com.crashlytics.android;

import com.crashlytics.android.internal.v;
import java.util.concurrent.Callable;

// compiled from: SourceFile
final class i implements Callable<T> {
    private /* synthetic */ Callable a;

    i(ba r1_ba, Callable r2_Callable) {
        this.a = r2_Callable;
    }

    public final T call() throws Exception {
        try {
            return this.a.call();
        } catch (Exception e) {
            v.a().b().a(Crashlytics.TAG, "Failed to execute task.", e);
            return null;
        }
    }
}