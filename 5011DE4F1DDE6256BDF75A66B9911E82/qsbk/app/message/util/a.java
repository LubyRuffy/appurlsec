package qsbk.app.message.util;

import java.io.File;
import qsbk.app.message.util.ACache.ACacheManager;

// compiled from: ACache.java
class a implements Runnable {
    final /* synthetic */ ACacheManager a;

    a(ACacheManager r1_ACacheManager) {
        this.a = r1_ACacheManager;
    }

    public void run() {
        int r0i = 0;
        File[] r3_FileA = this.a.a.listFiles();
        if (r3_FileA != null) {
            int r4i = r3_FileA.length;
            int r1i = 0;
            int r2i = 0;
            while (r0i < r4i) {
                File r5_File = r3_FileA[r0i];
                r2i = (int) (((long) r2i) + ACacheManager.b(this.a, r5_File));
                r1i++;
                ACacheManager.b(this.a).put(r5_File, Long.valueOf(r5_File.lastModified()));
                r0i++;
            }
            ACacheManager.c(this.a).set((long) r2i);
            ACacheManager.d(this.a).set(r1i);
        }
    }
}