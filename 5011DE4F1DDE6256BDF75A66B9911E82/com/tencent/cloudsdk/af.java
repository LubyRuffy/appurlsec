package com.tencent.cloudsdk;

import android.text.format.Time;
import android.util.Log;
import com.baidu.kirin.KirinConfig;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public final class af {
    public static final af a;

    static {
        a = new af();
    }

    public final String a(int r2i) {
        switch (r2i) {
            case XListViewHeader.STATE_READY:
                return "V";
            case XListViewHeader.STATE_REFRESHING:
                return "D";
            case XListViewFooter.STATE_NODATA:
                return "I";
            case Base64.DONT_BREAK_LINES:
                return "W";
            case Base64.URL_SAFE:
                return "E";
            case Base64.ORDERED:
                return "A";
        }
        return "-";
    }

    public String a(int r7i, Thread r8_Thread, long r9j, String r11_String, String r12_String, Throwable r13_Throwable) {
        long r0j = r9j % 1000;
        Time r2_Time = new Time();
        r2_Time.set(r9j);
        StringBuilder r3_StringBuilder = new StringBuilder();
        r3_StringBuilder.append(a(r7i)).append('/').append(r2_Time.format("%Y-%m-%d %H:%M:%S")).append('.');
        if (r0j < 10) {
            r3_StringBuilder.append("00");
        } else if (r0j < 100) {
            r3_StringBuilder.append('0');
        }
        r3_StringBuilder.append(r0j).append(' ').append('[');
        if (r8_Thread == null) {
            r3_StringBuilder.append(KirinConfig.NO_RESULT);
        } else {
            r3_StringBuilder.append(r8_Thread.getName());
        }
        r3_StringBuilder.append(']').append('[').append(r11_String).append(']').append(' ').append(r12_String).append('\n');
        if (r13_Throwable != null) {
            r3_StringBuilder.append("* Exception : \n").append(Log.getStackTraceString(r13_Throwable)).append('\n');
        }
        return r3_StringBuilder.toString();
    }
}