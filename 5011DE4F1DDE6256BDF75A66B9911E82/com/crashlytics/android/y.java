package com.crashlytics.android;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.crashlytics.android.internal.ab;
import com.crashlytics.android.internal.v;

// compiled from: SourceFile
final class y {
    public final String a;
    public final int b;
    public final int c;
    public final int d;

    private y(String r1_String, int r2i, int r3i, int r4i) {
        this.a = r1_String;
        this.b = r2i;
        this.c = r3i;
        this.d = r4i;
    }

    public static y a(Context r6_Context, String r7_String) {
        if (r7_String != null) {
            try {
                int r2i = ab.h(r6_Context);
                v.a().b().a(Crashlytics.TAG, new StringBuilder("App icon resource ID is ").append(r2i).toString());
                Options r3_Options = new Options();
                r3_Options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(r6_Context.getResources(), r2i, r3_Options);
                return new y(r7_String, r2i, r3_Options.outWidth, r3_Options.outHeight);
            } catch (Exception e) {
                v.a().b().a(Crashlytics.TAG, "Failed to load icon", e);
            }
        }
        return null;
    }
}