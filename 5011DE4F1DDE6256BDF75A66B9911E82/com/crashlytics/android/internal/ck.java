package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

// compiled from: SourceFile
final class ck implements ActivityLifecycleCallbacks {
    private /* synthetic */ cj a;

    ck(cj r1_cj) {
        this.a = r1_cj;
    }

    public final void onActivityCreated(Activity r2_Activity, Bundle r3_Bundle) {
        v.a(this.a.a, r2_Activity);
    }

    public final void onActivityDestroyed(Activity r1_Activity) {
    }

    public final void onActivityPaused(Activity r1_Activity) {
    }

    public final void onActivityResumed(Activity r2_Activity) {
        v.a(this.a.a, r2_Activity);
    }

    public final void onActivitySaveInstanceState(Activity r1_Activity, Bundle r2_Bundle) {
    }

    public final void onActivityStarted(Activity r2_Activity) {
        v.a(this.a.a, r2_Activity);
    }

    public final void onActivityStopped(Activity r1_Activity) {
    }
}