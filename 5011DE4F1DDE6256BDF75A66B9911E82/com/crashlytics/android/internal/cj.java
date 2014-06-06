package com.crashlytics.android.internal;

import android.app.Application;

// compiled from: SourceFile
final class cj {
    final /* synthetic */ v a;

    private cj(v r1_v) {
        this.a = r1_v;
    }

    static /* synthetic */ void a(cj r1_cj, Application r2_Application) {
        if (r2_Application != null) {
            r2_Application.registerActivityLifecycleCallbacks(new ck(r1_cj));
        }
    }
}