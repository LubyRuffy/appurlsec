package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: SourceFile
final class r implements FilenameFilter {
    private final String a;

    public r(String r1_String) {
        this.a = r1_String;
    }

    public final boolean accept(File r2_File, String r3_String) {
        return r3_String.contains(this.a) && !r3_String.endsWith(".cls_temp");
    }
}