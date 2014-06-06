package com.zkmm.adsdk;

import java.io.File;
import java.util.Comparator;

// compiled from: SourceFile
final class r implements Comparator {
    r() {
    }

    public final /* synthetic */ int compare(Object r7_Object, Object r8_Object) {
        long r0j = ((File) r7_Object).lastModified() - ((File) r8_Object).lastModified();
        if (r0j > 0) {
            return 1;
        }
        if (r0j == 0) {
            return 0;
        }
        return -1;
    }

    public final boolean equals(Object r2_Object) {
        return true;
    }
}