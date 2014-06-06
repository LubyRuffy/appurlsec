package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: SourceFile
final class ac implements FilenameFilter {
    ac() {
    }

    public final boolean accept(File r2_File, String r3_String) {
        return r3_String.endsWith(".cls") && !r3_String.contains("Session");
    }
}