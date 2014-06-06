package com.crashlytics.android;

import com.crashlytics.android.internal.Z;
import com.crashlytics.android.internal.av;
import com.crashlytics.android.internal.ax;
import com.crashlytics.android.internal.ay;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.v;
import java.util.Iterator;
import java.util.Map.Entry;

// compiled from: SourceFile
final class w extends Z implements v {
    public w(String r2_String, String r3_String, av r4_av) {
        super(r2_String, r3_String, r4_av, ax.b);
    }

    public final boolean a(u r7_u) {
        ay r0_ay = b().a("X-CRASHLYTICS-API-KEY", r7_u.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", Crashlytics.getInstance().getVersion());
        Iterator r2_Iterator = r7_u.b.e().entrySet().iterator();
        ay r1_ay = r0_ay;
        while (r2_Iterator.hasNext()) {
            r1_ay = r1_ay.a((Entry) r2_Iterator.next());
        }
        z r0_z = r7_u.b;
        r0_ay = r1_ay.a("report[file]", r0_z.b(), "application/octet-stream", r0_z.d()).b("report[identifier]", r0_z.c());
        v.a().b().a(Crashlytics.TAG, new StringBuilder("Sending report to: ").append(a()).toString());
        int r1i = r0_ay.b();
        v.a().b().a(Crashlytics.TAG, new StringBuilder("Create report request ID: ").append(r0_ay.a("X-REQUEST-ID")).toString());
        v.a().b().a(Crashlytics.TAG, new StringBuilder("Result was: ").append(r1i).toString());
        return r.a(r1i) == 0;
    }
}