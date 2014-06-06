package com.tencent.cloudsdk;

import java.io.File;

// compiled from: SourceFile
public class am {
    public static boolean a(File r4_File) {
        boolean r0z = false;
        if (r4_File == null) {
            return false;
        }
        if (r4_File.isFile()) {
            if (r4_File.delete()) {
                return true;
            }
            r4_File.deleteOnExit();
            return false;
        } else {
            if (!r4_File.isDirectory()) {
                return false;
            }
            File[] r1_FileA = r4_File.listFiles();
            boolean r2z = r1_FileA.length;
            while (r0z < r2z) {
                a(r1_FileA[r0z]);
                r0z++;
            }
            return r4_File.delete();
        }
    }
}