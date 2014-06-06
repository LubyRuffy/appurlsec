package qsbk.app.utils.image.issue;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: Reporter.java
class c implements FilenameFilter {
    final /* synthetic */ b a;

    c(b r1_b) {
        this.a = r1_b;
    }

    public boolean accept(File r2_File, String r3_String) {
        return r3_String.endsWith(".sent");
    }
}