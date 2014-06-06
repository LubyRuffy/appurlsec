package qsbk.app.exception;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: CrashHandler.java
class b implements FilenameFilter {
    final /* synthetic */ CrashHandler a;

    b(CrashHandler r1_CrashHandler) {
        this.a = r1_CrashHandler;
    }

    public boolean accept(File r2_File, String r3_String) {
        return r3_String.endsWith(".sent");
    }
}