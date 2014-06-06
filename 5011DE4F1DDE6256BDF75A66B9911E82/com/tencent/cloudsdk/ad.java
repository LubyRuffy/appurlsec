package com.tencent.cloudsdk;

import android.util.Log;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public final class ad extends ag {
    public static final ad a;

    static {
        a = new ad();
    }

    protected void a(int r1i, Thread r2_Thread, long r3j, String r5_String, String r6_String, Throwable r7_Throwable) {
        switch (r1i) {
            case XListViewHeader.STATE_READY:
                Log.v(r5_String, r6_String, r7_Throwable);
                break;
            case XListViewHeader.STATE_REFRESHING:
                Log.d(r5_String, r6_String, r7_Throwable);
                break;
            case XListViewFooter.STATE_NODATA:
                Log.i(r5_String, r6_String, r7_Throwable);
                break;
            case Base64.DONT_BREAK_LINES:
                Log.w(r5_String, r6_String, r7_Throwable);
                break;
            case Base64.URL_SAFE:
                Log.e(r5_String, r6_String, r7_Throwable);
                break;
            case Base64.ORDERED:
                Log.e(r5_String, r6_String, r7_Throwable);
                break;
        }
    }
}