package com.tencent.cloudsdk;

import java.io.File;
import java.util.Comparator;

// compiled from: SourceFile
class ab implements Comparator {
    final /* synthetic */ z a;

    ab(z r1_z) {
        this.a = r1_z;
    }

    public int a(File r3_File, File r4_File) {
        return z.d(r3_File) - z.d(r4_File);
    }

    public /* synthetic */ int compare(Object r2_Object, Object r3_Object) {
        return a((File) r2_Object, (File) r3_Object);
    }
}