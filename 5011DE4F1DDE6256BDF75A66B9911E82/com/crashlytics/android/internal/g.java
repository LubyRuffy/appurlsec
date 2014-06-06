package com.crashlytics.android.internal;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import java.util.concurrent.ScheduledExecutorService;

// compiled from: SourceFile
final class g extends ac {
    private final Application b;
    private final ActivityLifecycleCallbacks c;

    public g(Application r13_Application, String r14_String, String r15_String, String r16_String, String r17_String, String r18_String, String r19_String, String r20_String, o r21_o, av r22_av) {
        this(r13_Application, r14_String, r15_String, r16_String, r17_String, r18_String, r19_String, r20_String, r21_o, ah.b("Crashlytics Trace Manager"), r22_av);
    }

    private g(Application r12_Application, String r13_String, String r14_String, String r15_String, String r16_String, String r17_String, String r18_String, String r19_String, o r20_o, ScheduledExecutorService r21_ScheduledExecutorService, av r22_av) {
        super(r13_String, r14_String, r15_String, r16_String, r17_String, r18_String, r19_String, r20_o, r21_ScheduledExecutorService, r22_av);
        this.c = new h(this);
        this.b = r12_Application;
        ab.c("Registering activity lifecycle callbacks for session analytics.");
        r12_Application.registerActivityLifecycleCallbacks(this.c);
    }

    final void a() {
        ab.c("Unregistering activity lifecycle callbacks for session analytics");
        this.b.unregisterActivityLifecycleCallbacks(this.c);
        super.a();
    }
}