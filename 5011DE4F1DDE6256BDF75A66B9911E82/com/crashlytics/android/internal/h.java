package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

// compiled from: SourceFile
final class h implements ActivityLifecycleCallbacks {
    private /* synthetic */ g a;

    h(g r1_g) {
        this.a = r1_g;
    }

    public final void onActivityCreated(Activity r2_Activity, Bundle r3_Bundle) {
        this.a.a(r2_Activity);
    }

    public final void onActivityDestroyed(Activity r2_Activity) {
        this.a.b(r2_Activity);
    }

    public final void onActivityPaused(Activity r2_Activity) {
        this.a.c(r2_Activity);
    }

    public final void onActivityResumed(Activity r2_Activity) {
        this.a.d(r2_Activity);
    }

    public final void onActivitySaveInstanceState(Activity r2_Activity, Bundle r3_Bundle) {
        this.a.e(r2_Activity);
    }

    public final void onActivityStarted(Activity r2_Activity) {
        this.a.f(r2_Activity);
    }

    public final void onActivityStopped(Activity r2_Activity) {
        this.a.g(r2_Activity);
    }
}