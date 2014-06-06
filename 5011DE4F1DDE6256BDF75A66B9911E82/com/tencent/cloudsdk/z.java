package com.tencent.cloudsdk;

import com.qiubai.library.adview.util.AdViewUtil;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

// compiled from: SourceFile
public class z {
    private static SimpleDateFormat a;
    private static FileFilter b;
    private String c;
    private int d;
    private int e;
    private int f;
    private long g;
    private File h;
    private int i;
    private String j;
    private long k;
    private FileFilter l;
    private Comparator m;

    static {
        a = an.a("yyyy-MM-dd");
        b = new v();
    }

    public z(File r3_File, int r4i, int r5i, int r6i, String r7_String, long r8j, int r10i, String r11_String, long r12j) {
        this.c = "Tracer.File";
        this.d = 2147483647;
        this.e = 2147483647;
        this.f = 4096;
        this.g = 10000;
        this.i = 10;
        this.j = ".log";
        this.k = 9223372036854775807L;
        this.l = new aa(this);
        this.m = new ab(this);
        c(r3_File);
        b(r4i);
        a(r5i);
        c(r6i);
        a(r7_String);
        b(r8j);
        d(r10i);
        b(r11_String);
        c(r12j);
    }

    public static long a(File r2_File) {
        try {
            return a.parse(r2_File.getName()).getTime();
        } catch (Exception e) {
            return -1;
        }
    }

    private File d(long r2j) {
        return e(a(r2j));
    }

    private File e(File r7_File) {
        File[] r3_FileA = b(r7_File);
        if (r3_FileA == null || r3_FileA.length == 0) {
            return new File(r7_File, new StringBuilder("1").append(j()).toString());
        }
        a(r3_FileA);
        File r1_File = r3_FileA[r3_FileA.length - 1];
        int r0i = r3_FileA.length - e();
        if (((int) r1_File.length()) > d()) {
            r1_File = new File(r7_File, new StringBuilder(String.valueOf(f(r1_File) + 1)).append(j()).toString());
            r0i++;
        }
        int r2i = 0;
        while (r2i < r0i) {
            r3_FileA[r2i].delete();
            r2i++;
        }
        return r1_File;
    }

    private static int f(File r3_File) {
        try {
            String r0_String = r3_File.getName();
            return Integer.parseInt(r0_String.substring(0, r0_String.indexOf(AdViewUtil.NETWORK_TYPE_MOBWIN)));
        } catch (Exception e) {
            return -1;
        }
    }

    public File a() {
        return d(System.currentTimeMillis());
    }

    public File a(long r5j) {
        File r0_File = new File(h(), a.format(Long.valueOf(r5j)));
        r0_File.mkdirs();
        return r0_File;
    }

    public void a(int r1i) {
        this.d = r1i;
    }

    public void a(String r1_String) {
        this.c = r1_String;
    }

    public File[] a(File[] r2_FileA) {
        Arrays.sort(r2_FileA, this.m);
        return r2_FileA;
    }

    public void b() {
        if (h() == null) {
        } else {
            File[] r1_FileA = h().listFiles(b);
            if (r1_FileA != null) {
                int r2i = r1_FileA.length;
                int r0i = 0;
                while (r0i < r2i) {
                    File r3_File = r1_FileA[r0i];
                    if (System.currentTimeMillis() - a(r3_File) > k()) {
                        am.a(r3_File);
                    }
                    r0i++;
                }
            }
        }
    }

    public void b(int r1i) {
        this.e = r1i;
    }

    public void b(long r1j) {
        this.g = r1j;
    }

    public void b(String r1_String) {
        this.j = r1_String;
    }

    public File[] b(File r2_File) {
        return r2_File.listFiles(this.l);
    }

    public String c() {
        return this.c;
    }

    public void c(int r1i) {
        this.f = r1i;
    }

    public void c(long r1j) {
        this.k = r1j;
    }

    public void c(File r1_File) {
        this.h = r1_File;
    }

    public int d() {
        return this.d;
    }

    public void d(int r1i) {
        this.i = r1i;
    }

    public int e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public long g() {
        return this.g;
    }

    public File h() {
        return this.h;
    }

    public int i() {
        return this.i;
    }

    public String j() {
        return this.j;
    }

    public long k() {
        return this.k;
    }
}