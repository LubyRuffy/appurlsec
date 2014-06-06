package qsbk.app.cache;

import java.io.File;
import java.io.FilenameFilter;

// compiled from: DiskLruCache.java
final class a implements FilenameFilter {
    a() {
    }

    public boolean accept(File r2_File, String r3_String) {
        return r3_String.startsWith("cache_");
    }
}