package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: SourceFile
final class q implements FilenameFilter {
    private q() {
    }

    public final boolean accept(File r2_File, String r3_String) {
        return !ba.a.accept(r2_File, r3_String) && ba.i().matcher(r3_String).matches();
    }
}