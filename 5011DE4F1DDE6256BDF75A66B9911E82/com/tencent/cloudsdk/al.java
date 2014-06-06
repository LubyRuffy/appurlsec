package com.tencent.cloudsdk;

import android.os.StatFs;
import java.io.File;

// compiled from: SourceFile
public class al {
    private File a;
    private long b;
    private long c;

    public static al b(File r8_File) {
        al r0_al = new al();
        r0_al.a(r8_File);
        StatFs r1_StatFs = new StatFs(r8_File.getAbsolutePath());
        long r2j = (long) r1_StatFs.getBlockSize();
        r0_al.a(((long) r1_StatFs.getBlockCount()) * r2j);
        r0_al.b(((long) r1_StatFs.getAvailableBlocks()) * r2j);
        return r0_al;
    }

    public File a() {
        return this.a;
    }

    public void a(long r1j) {
        this.b = r1j;
    }

    public void a(File r1_File) {
        this.a = r1_File;
    }

    public long b() {
        return this.b;
    }

    public void b(long r1j) {
        this.c = r1j;
    }

    public long c() {
        return this.c;
    }

    public String toString() {
        Object[] r1_ObjectA = new Object[3];
        r1_ObjectA[0] = a().getAbsolutePath();
        r1_ObjectA[1] = Long.valueOf(c());
        r1_ObjectA[2] = Long.valueOf(b());
        return String.format("[%s : %d / %d]", r1_ObjectA);
    }
}