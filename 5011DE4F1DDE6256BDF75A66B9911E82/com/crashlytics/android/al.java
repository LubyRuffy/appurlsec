package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: SourceFile
final class al implements FilenameFilter {
    al() {
    }

    public final boolean accept(File r2_File, String r3_String) {
        return r3_String.endsWith(".cls_temp");
    }
}