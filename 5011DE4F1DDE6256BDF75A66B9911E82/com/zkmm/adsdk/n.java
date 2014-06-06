package com.zkmm.adsdk;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.util.Arrays;

// compiled from: SourceFile
final class n implements Runnable {
    private final /* synthetic */ Context a;

    n(Context r1_Context) {
        this.a = r1_Context;
    }

    public final void run() {
        File r1_File = new File(m.O);
        if (r1_File.exists()) {
            File[] r2_FileA = r1_File.listFiles(new p(this));
            int r0i = r2_FileA.length;
            if (r0i > m.c()) {
                Arrays.sort(r2_FileA, new r());
                int r3i = r0i - m.c();
                r0i = 0;
                while (r0i < r3i) {
                    s.a(r2_FileA[r0i]);
                    r0i++;
                }
            }
            File[] r10_FileA = r1_File.listFiles();
            int r11i = r10_FileA.length;
            int r7i = 0;
            while (r7i < r11i) {
                String r0_String = r10_FileA[r7i].getAbsolutePath();
                if (r0_String.endsWith(".tmp")) {
                    r0_String = s.h(this.a, r0_String);
                    if (r0_String != null) {
                        try {
                            Log.e("Adwo SDK", new StringBuilder("Unfinished Url-->").append(r0_String).toString());
                            String[] r5_StringA = r0_String.split(",,,");
                            s.a(r5_StringA[0], this.a, !"0".equals(r5_StringA[1]), Integer.parseInt(r5_StringA[2]), !"0".equals(r5_StringA[3]), r5_StringA[4], "0".equals(r5_StringA[5]) ? (short) 0 : Short.parseShort(r5_StringA[5]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                r7i++;
            }
        } else {
            r1_File.mkdirs();
        }
    }
}