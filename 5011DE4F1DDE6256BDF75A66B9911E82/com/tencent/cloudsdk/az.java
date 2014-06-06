package com.tencent.cloudsdk;

import java.util.Comparator;

// compiled from: SourceFile
class az implements Comparator {
    private az() {
    }

    public int a(ch r3_ch, ch r4_ch) {
        return r3_ch.d() > r4_ch.d() ? 1 : -1;
    }

    public /* synthetic */ int compare(Object r2_Object, Object r3_Object) {
        return a((ch) r2_Object, (ch) r3_Object);
    }
}