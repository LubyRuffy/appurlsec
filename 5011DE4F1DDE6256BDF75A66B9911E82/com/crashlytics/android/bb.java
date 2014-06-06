package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: SourceFile
final class bb implements FilenameFilter {
    bb() {
    }

    public final boolean accept(File r3_File, String r4_String) {
        return r4_String.length() == 39 && r4_String.endsWith(".cls");
    }
}