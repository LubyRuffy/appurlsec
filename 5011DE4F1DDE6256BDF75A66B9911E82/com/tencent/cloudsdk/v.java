package com.tencent.cloudsdk;

import java.io.File;
import java.io.FileFilter;

// compiled from: SourceFile
class v implements FileFilter {
    v() {
    }

    public boolean accept(File r6_File) {
        return r6_File.isDirectory() && z.a(r6_File) > 0;
    }
}