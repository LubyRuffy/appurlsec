package com.tencent.qc.stat.common;

import java.io.File;

// compiled from: ProGuard
class a {
    private static int a;

    static {
        a = -1;
    }

    public static boolean a_() {
        if (a == 1) {
            return true;
        }
        if (a == 0) {
            return false;
        }
        String[] r3_StringA = new String[6];
        r3_StringA[0] = "/bin";
        r3_StringA[1] = "/system/bin/";
        r3_StringA[2] = "/system/xbin/";
        r3_StringA[3] = "/system/sbin/";
        r3_StringA[4] = "/sbin/";
        r3_StringA[5] = "/vendor/bin/";
        int r2i = 0;
        while (true) {
            try {
                if (r2i < r3_StringA.length) {
                    File r4_File = new File(r3_StringA[r2i] + "su");
                    if (r4_File == null || (!r4_File.exists())) {
                        r2i++;
                    } else {
                        a = 1;
                        return true;
                    }
                } else {
                    a = 0;
                    return false;
                }
            } catch (Exception e) {
            }
        }
    }
}