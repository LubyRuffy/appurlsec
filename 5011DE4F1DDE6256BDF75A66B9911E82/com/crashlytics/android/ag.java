package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

// compiled from: SourceFile
class ag {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final int g;
    public final String h;
    public final String i;
    public final y j;

    public ag(String r1_String, String r2_String, String r3_String, String r4_String, String r5_String, String r6_String, int r7i, String r8_String, String r9_String, y r10_y) {
        this.a = r1_String;
        this.b = r2_String;
        this.c = r3_String;
        this.d = r4_String;
        this.e = r5_String;
        this.f = r6_String;
        this.g = r7i;
        this.h = r8_String;
        this.i = r9_String;
        this.j = r10_y;
    }

    public static void a(File r5_File, FilenameFilter r6_FilenameFilter, int r7i, Comparator<File> r8_Comparator_File) {
        File[] r2_FileA = r5_File.listFiles(r6_FilenameFilter);
        if (r2_FileA == null || r2_FileA.length <= r7i) {
        } else {
            Arrays.sort(r2_FileA, r8_Comparator_File);
            int r1i = r2_FileA.length;
            int r3i = r2_FileA.length;
            int r0i = 0;
            while (r0i < r3i) {
                File r4_File = r2_FileA[r0i];
                if (r1i <= r7i) {
                } else {
                    r4_File.delete();
                    r1i--;
                    r0i++;
                }
            }
        }
    }
}