package com.tencent.cloudsdk;

import java.io.File;
import java.io.FileFilter;

// compiled from: SourceFile
class aa implements FileFilter {
    final /* synthetic */ z a;

    aa(z r1_z) {
        this.a = r1_z;
    }

    public boolean accept(File r4_File) {
        return r4_File.getName().endsWith(this.a.j()) && z.d(r4_File) != -1;
    }
}