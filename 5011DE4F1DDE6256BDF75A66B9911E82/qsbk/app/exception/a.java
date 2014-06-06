package qsbk.app.exception;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: CrashHandler.java
class a implements FilenameFilter {
    final /* synthetic */ CrashHandler a;

    a(CrashHandler r1_CrashHandler) {
        this.a = r1_CrashHandler;
    }

    public boolean accept(File r2_File, String r3_String) {
        return r3_String.endsWith(".cr") || r3_String.endsWith(".cr.qbk");
    }
}