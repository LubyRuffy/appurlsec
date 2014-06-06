package qsbk.app.utils.image.issue;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: Reporter.java
class e implements FilenameFilter {
    final /* synthetic */ Reporter a;

    e(Reporter r1_Reporter) {
        this.a = r1_Reporter;
    }

    public boolean accept(File r2_File, String r3_String) {
        return r3_String.endsWith(".di");
    }
}