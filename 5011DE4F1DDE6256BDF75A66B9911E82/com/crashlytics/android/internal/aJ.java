package com.crashlytics.android.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
public class aj {
    private final File a;
    private final String b;
    private aq c;
    private File d;
    private File e;

    public aj(File r4_File, String r5_String, String r6_String) throws IOException {
        this.a = r4_File;
        this.b = r6_String;
        this.d = new File(r4_File, r5_String);
        this.c = new aq(this.d);
        this.e = new File(this.a, this.b);
        if (!this.e.exists()) {
            this.e.mkdirs();
        }
    }

    public int a() {
        return this.c.a();
    }

    public List<File> a(int r6i) {
        List<File> r1_List_File = new ArrayList();
        File[] r2_FileA = this.e.listFiles();
        int r3i = r2_FileA.length;
        int r0i = 0;
        while (r0i < r3i) {
            r1_List_File.add(r2_FileA[r0i]);
            if (r1_List_File.size() > 0) {
                return r1_List_File;
            }
            r0i++;
        }
        return r1_List_File;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(String r7_String) throws IOException {
        /*
        r6_this = this;
        r2 = 0;
        r0 = r6.c;
        r0.close();
        r4 = r6.d;
        r0 = new java.io.File;
        r1 = r6.e;
        r0.<init>(r1, r7);
        r1 = new java.io.FileInputStream;	 //Catch:{ all -> 0x003c }
        r1.<init>(r4);	 //Catch:{ all -> 0x003c }
        r3 = new java.util.zip.GZIPOutputStream;	 //Catch:{ all -> 0x004c }
        r5 = new java.io.FileOutputStream;	 //Catch:{ all -> 0x004c }
        r5.<init>(r0);	 //Catch:{ all -> 0x004c }
        r3.<init>(r5);	 //Catch:{ all -> 0x004c }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r0];	 //Catch:{ all -> 0x004e }
        com.crashlytics.android.internal.ab.a(r1, r3, r0);	 //Catch:{ all -> 0x004e }
        r0 = "Failed to close file input stream";
        com.crashlytics.android.internal.ab.a(r1, r0);
        r0 = "Failed to close gzip output stream";
        com.crashlytics.android.internal.ab.a(r3, r0);
        r4.delete();
        r0 = new com.crashlytics.android.internal.aq;
        r1 = r6.d;
        r0.<init>(r1);
        r6.c = r0;
        return;
    L_0x003c:
        r0 = move-exception;
        r1 = r2;
    L_0x003e:
        r3 = "Failed to close file input stream";
        com.crashlytics.android.internal.ab.a(r1, r3);
        r1 = "Failed to close gzip output stream";
        com.crashlytics.android.internal.ab.a(r2, r1);
        r4.delete();
        throw r0;
    L_0x004c:
        r0 = move-exception;
        goto L_0x003e;
    L_0x004e:
        r0 = move-exception;
        r2 = r3;
        goto L_0x003e;
        */

    }

    public void a(List<File> r7_List_File) {
        Iterator r1_Iterator = r7_List_File.iterator();
        while (r1_Iterator.hasNext()) {
            File r0_File = (File) r1_Iterator.next();
            Object[] r3_ObjectA = new Object[1];
            r3_ObjectA[0] = r0_File.getName();
            ab.c(String.format("deleting sent analytics file %s", r3_ObjectA));
            r0_File.delete();
        }
    }

    public void a(byte[] r2_byteA) throws IOException {
        this.c.a(r2_byteA);
    }

    public boolean a(int r2i, int r3i) {
        return this.c.a(r2i, r3i);
    }

    public boolean b() {
        return this.c.b();
    }

    public List<File> c() {
        return Arrays.asList(this.e.listFiles());
    }

    public void d() {
        try {
            this.c.close();
        } catch (IOException e) {
        }
        this.d.delete();
    }
}