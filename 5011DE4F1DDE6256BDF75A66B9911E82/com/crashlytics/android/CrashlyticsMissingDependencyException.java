package com.crashlytics.android;

import com.crashlytics.android.internal.v;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import qsbk.app.bean.Base;

// compiled from: SourceFile
public class CrashlyticsMissingDependencyException extends RuntimeException {
    CrashlyticsMissingDependencyException(String r2_String, String r3_String) {
        super(a(r2_String, r3_String));
    }

    private static String a(String r6_String, String r7_String) {
        StringBuilder r1_StringBuilder = new StringBuilder();
        try {
            r1_StringBuilder.append("\nThis app relies on Crashlytics. Configure your build environment here: \n");
            StringBuilder r0_StringBuilder = new StringBuilder();
            Object[] r3_ObjectA = new Object[2];
            r3_ObjectA[0] = URLEncoder.encode(r6_String, Base.UTF8);
            r3_ObjectA[1] = URLEncoder.encode(r7_String, Base.UTF8);
            r1_StringBuilder.append(r0_StringBuilder.append(String.format("https://crashlytics.com/register/%s/android/%s", r3_ObjectA)).append("\n").toString());
        } catch (UnsupportedEncodingException e) {
            v.a().b().a(TAG, "Could not find UTF-8 encoding.", e);
        }
        return r1_StringBuilder.toString();
    }
}