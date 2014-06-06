package com.crashlytics.android;

import android.content.Context;
import com.crashlytics.android.internal.aQ;
import com.crashlytics.android.internal.ab;

// compiled from: SourceFile
final class x {
    private final Context a;
    private final aQ b;

    public x(Context r1_Context, aQ r2_aQ) {
        this.a = r1_Context;
        this.b = r2_aQ;
    }

    private String a(String r3_String, String r4_String) {
        String r0_String = ab.a(this.a, r3_String);
        int r1i = (r0_String == null || r0_String.length() == 0) ? 1 : 0;
        return r1i != 0 ? r4_String : r0_String;
    }

    public final String a() {
        return a("com.crashlytics.CrashSubmissionPromptTitle", this.b.a);
    }

    public final String b() {
        return a("com.crashlytics.CrashSubmissionPromptMessage", this.b.b);
    }

    public final String c() {
        return a("com.crashlytics.CrashSubmissionSendTitle", this.b.c);
    }

    public final String d() {
        return a("com.crashlytics.CrashSubmissionAlwaysSendTitle", this.b.g);
    }

    public final String e() {
        return a("com.crashlytics.CrashSubmissionCancelTitle", this.b.e);
    }
}