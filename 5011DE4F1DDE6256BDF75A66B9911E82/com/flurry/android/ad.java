package com.flurry.android;

import android.content.Context;
import android.widget.ImageView;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import qsbk.app.bean.Base;

// compiled from: SourceFile
final class ad {
    static int a(Context r2_Context, int r3i) {
        return (int) (r2_Context.getResources().getDisplayMetrics().density * ((float) r3i) + 0.5f);
    }

    static String a(String r3_String) {
        try {
            return URLEncoder.encode(r3_String, Base.UTF8);
        } catch (UnsupportedEncodingException e) {
            i.d("FlurryAgent", "Cannot encode '" + r3_String + "'");
            return RContactStorage.PRIMARY_KEY;
        }
    }

    static String a(String r1_String, int r2i) {
        if (r1_String == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        if (r1_String.length() > r2i) {
            return r1_String.substring(0, r2i);
        }
        return r1_String;
    }

    static void a(Context r1_Context, ImageView r2_ImageView, int r3i, int r4i) {
        r2_ImageView.setAdjustViewBounds(true);
        r2_ImageView.setMinimumWidth(a(r1_Context, r3i));
        r2_ImageView.setMinimumHeight(a(r1_Context, r4i));
        r2_ImageView.setMaxWidth(a(r1_Context, r3i));
        r2_ImageView.setMaxHeight(a(r1_Context, r4i));
    }

    static void a(Closeable r1_Closeable) {
        if (r1_Closeable != null) {
            try {
                r1_Closeable.close();
            } catch (Throwable th) {
            }
        }
    }
}