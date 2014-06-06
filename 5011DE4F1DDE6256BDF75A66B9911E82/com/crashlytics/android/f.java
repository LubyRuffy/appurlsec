package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: SourceFile
final class f implements FilenameFilter {
    private /* synthetic */ String a;

    f(ba r1_ba, String r2_String) {
        this.a = r2_String;
    }

    public final boolean accept(File r2_File, String r3_String) {
        return r3_String.startsWith(this.a);
    }
}