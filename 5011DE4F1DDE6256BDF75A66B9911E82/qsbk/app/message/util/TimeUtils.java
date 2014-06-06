package qsbk.app.message.util;

import android.text.format.DateFormat;

public class TimeUtils {
    private static long a;
    private static long b;
    private static long c;
    private static long d;

    static {
        a = 604800000;
        b = 86400000;
        c = 3600000;
        d = 60000;
    }

    public static String getLastLoginStr(long r6j) {
        long r0j = System.currentTimeMillis() - r6j;
        StringBuffer r2_StringBuffer = new StringBuffer();
        if (r0j > a) {
            if (((int) (r0j / a)) > 1) {
                r2_StringBuffer.append(DateFormat.format("MM-dd", r6j));
            } else {
                r2_StringBuffer.append(((int) (r0j / a)) + "\u5468\u524d");
            }
        } else if (r0j > b) {
            r2_StringBuffer.append(((int) (r0j / b)) + "\u5929\u524d");
        } else if (r0j > c) {
            r2_StringBuffer.append(((int) (r0j / c)) + "\u5c0f\u65f6\u524d");
        } else if (r0j > d) {
            r2_StringBuffer.append(((int) (r0j / d)) + "\u5206\u949f\u524d");
        } else {
            r2_StringBuffer.append("\u521a\u521a");
        }
        return r2_StringBuffer.toString();
    }
}