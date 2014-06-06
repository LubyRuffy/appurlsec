package com.google.analytics.tracking.android;

import android.os.Build.VERSION;
import java.io.File;

// compiled from: FutureApis.java
class i {
    static boolean a(String r4_String) {
        boolean r0z = false;
        if (version() < 9) {
            return false;
        }
        File r2_File = new File(r4_String);
        r2_File.setReadable(r0z, r0z);
        r2_File.setWritable(r0z, r0z);
        r2_File.setReadable(true, true);
        r2_File.setWritable(true, true);
        return true;
    }

    public static int version() {
        try {
            return Integer.parseInt(VERSION.SDK);
        } catch (NumberFormatException e) {
            z.c("Invalid version number: " + VERSION.SDK);
            return 0;
        }
    }
}