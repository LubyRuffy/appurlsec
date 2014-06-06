package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: SourceFile
final class k implements FilenameFilter {
    k() {
    }

    public final boolean accept(File r2_File, String r3_String) {
        return ba.i().matcher(r3_String).matches();
    }
}