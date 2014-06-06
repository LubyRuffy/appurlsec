package com.crashlytics.android;

import android.util.Log;
import com.crashlytics.android.internal.ab;
import com.crashlytics.android.internal.v;

// compiled from: SourceFile
final class ah {
    private String a;
    private boolean b;

    public ah(String r1_String, boolean r2z) {
        this.a = r1_String;
        this.b = r2z;
    }

    public final void a(String r5_String, String r6_String) {
        if (ab.e(this.a) && this.b) {
            Log.e(Crashlytics.TAG, ".");
            Log.e(Crashlytics.TAG, ".     |  | ");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".   \\ |  | /");
            Log.e(Crashlytics.TAG, ".    \\    /");
            Log.e(Crashlytics.TAG, ".     \\  /");
            Log.e(Crashlytics.TAG, ".      \\/");
            Log.e(Crashlytics.TAG, ".");
            Log.e(Crashlytics.TAG, "This app relies on Crashlytics. Configure your build environment here: ");
            String r0_String = Crashlytics.TAG;
            Object[] r2_ObjectA = new Object[2];
            r2_ObjectA[0] = r5_String;
            r2_ObjectA[1] = r6_String;
            Log.e(r0_String, String.format("https://crashlytics.com/register/%s/android/%s", r2_ObjectA));
            Log.e(Crashlytics.TAG, ".");
            Log.e(Crashlytics.TAG, ".      /\\");
            Log.e(Crashlytics.TAG, ".     /  \\");
            Log.e(Crashlytics.TAG, ".    /    \\");
            Log.e(Crashlytics.TAG, ".   / |  | \\");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".");
            throw new CrashlyticsMissingDependencyException(r5_String, r6_String);
        } else {
            if (!this.b) {
                v.a().b().a(Crashlytics.TAG, "Configured not to require a build ID.");
            }
        }
    }
}