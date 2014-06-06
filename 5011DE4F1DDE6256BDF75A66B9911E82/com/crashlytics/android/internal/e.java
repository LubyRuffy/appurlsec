package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;

// compiled from: SourceFile
final class e extends aa {
    private /* synthetic */ D a;

    e(D r1_D) {
        this.a = r1_D;
    }

    public final void a() {
        try {
            D.a(this.a);
        } catch (Exception e) {
            v.a().b().a(Crashlytics.TAG, "Problem encountered during Crashlytics initialization.", e);
        }
    }
}