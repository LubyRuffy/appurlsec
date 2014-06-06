package com.crashlytics.android.internal;

import android.os.Looper;

// compiled from: SourceFile
final class ci implements m {
    ci() {
    }

    public final void a(b r4_b) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException(new StringBuilder("Event bus ").append(r4_b).append(" accessed from non-main thread ").append(Looper.myLooper()).toString());
        }
    }
}