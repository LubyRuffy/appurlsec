package com.crashlytics.android;

import android.content.res.Resources.NotFoundException;
import com.crashlytics.android.internal.Z;
import com.crashlytics.android.internal.ab;
import com.crashlytics.android.internal.av;
import com.crashlytics.android.internal.ax;
import com.crashlytics.android.internal.ay;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.v;
import java.io.Closeable;
import java.io.InputStream;
import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.utils.audit.Rotate3dAnimation;

// compiled from: SourceFile
abstract class aa extends Z {
    public aa(String r1_String, String r2_String, av r3_av, ax r4_ax) {
        super(r1_String, r2_String, r3_av, r4_ax);
    }

    private static ay a(ay r7_ay, ag r8_ag) {
        ay r2_ay = r7_ay.b("app[identifier]", r8_ag.b).b("app[name]", r8_ag.f).b("app[display_version]", r8_ag.c).b("app[build_version]", r8_ag.d).a("app[source]", Integer.valueOf(r8_ag.g)).b("app[minimum_sdk_version]", r8_ag.h).b("app[built_sdk_version]", r8_ag.i);
        if (!ab.e(r8_ag.e)) {
            r2_ay.b("app[instance_identifier]", r8_ag.e);
        }
        if (r8_ag.j != null) {
            try {
                Closeable r1_Closeable = v.a().getContext().getResources().openRawResource(r8_ag.j.b);
                r2_ay.b("app[icon][hash]", r8_ag.j.a).a("app[icon][data]", "icon.png", "application/octet-stream", (InputStream)r1_Closeable).a("app[icon][width]", Integer.valueOf(r8_ag.j.c)).a("app[icon][height]", Integer.valueOf(r8_ag.j.d));
                ab.a(r1_Closeable, "Failed to close app icon InputStream.");
            } catch (NotFoundException e) {
                v.a().b().a(Crashlytics.TAG, new StringBuilder("Failed to find app icon with resource ID: ").append(r8_ag.j.b).toString(), e);
                ab.a(null, "Failed to close app icon InputStream.");
            }
        }
        return r2_ay;
    }

    public final boolean a(ag r7_ag) {
        ay r1_ay = a(b().a("X-CRASHLYTICS-API-KEY", r7_ag.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", v.a().getVersion()), r7_ag);
        v.a().b().a(Crashlytics.TAG, new StringBuilder("Sending app info to ").append(a()).toString());
        if (r7_ag.j != null) {
            v.a().b().a(Crashlytics.TAG, new StringBuilder("App icon hash is ").append(r7_ag.j.a).toString());
            v.a().b().a(Crashlytics.TAG, new StringBuilder("App icon size is ").append(r7_ag.j.c).append(Rotate3dAnimation.ROTATE_X).append(r7_ag.j.d).toString());
        }
        int r2i = r1_ay.b();
        v.a().b().a(Crashlytics.TAG, (UsersAPI.HTTPMETHOD_POST.equals(r1_ay.d()) ? "Create" : "Update") + " app request ID: " + r1_ay.a("X-REQUEST-ID"));
        v.a().b().a(Crashlytics.TAG, new StringBuilder("Result was ").append(r2i).toString());
        return r.a(r2i) == 0;
    }
}