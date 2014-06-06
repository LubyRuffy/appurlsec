package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: SourceFile
final class s implements FilenameFilter {
    private final String a;

    public s(String r1_String) {
        this.a = r1_String;
    }

    public final boolean accept(File r4_File, String r5_String) {
        return !r5_String.equals(new StringBuilder().append(this.a).append(".cls").toString()) && r5_String.contains(this.a) && !r5_String.endsWith(".cls_temp");
    }
}