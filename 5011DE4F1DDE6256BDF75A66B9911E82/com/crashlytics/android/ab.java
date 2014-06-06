package com.crashlytics.android;

import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.v;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// compiled from: SourceFile
final class ab {
    static final Map<String, String> a;
    private static final FilenameFilter b;
    private static final short[] c;
    private final Object d;
    private final v e;
    private Thread f;

    static {
        b = new ac();
        a = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
        c = new short[]{(short) 10, (short) 20, (short) 30, (short) 60, (short) 120, (short) 300};
    }

    public ab(v r3_v) {
        this.d = new Object();
        if (r3_v == null) {
            throw new IllegalArgumentException("createReportCall must not be null.");
        } else {
            this.e = r3_v;
        }
    }

    final List<z> a() {
        File[] r2_FileA;
        v.a().b().a(Crashlytics.TAG, "Checking for crash reports...");
        synchronized (this.d) {
            r2_FileA = v.a().h().listFiles(b);
        }
        List<z> r1_List_z = new LinkedList();
        int r3i = r2_FileA.length;
        int r0i = 0;
        while (r0i < r3i) {
            File r4_File = r2_FileA[r0i];
            v.a().b().a(Crashlytics.TAG, new StringBuilder("Found crash report ").append(r4_File.getPath()).toString());
            r1_List_z.add(new z(r4_File));
            r0i++;
        }
        if (r1_List_z.size() == 0) {
            v.a().b().a(Crashlytics.TAG, "No reports found.");
        }
        return r1_List_z;
    }

    public final synchronized void a(float r4f) {
        if (this.f == null) {
            this.f = new Thread(new ad(this, r4f), "Crashlytics Report Uploader");
            this.f.start();
        }
    }

    final boolean a(z r8_z) {
        boolean r0z = false;
        synchronized (this.d) {
            try {
                boolean r3z = this.e.a(new u(r.a(v.a().getContext(), v.a().f()), r8_z));
                v.a().b().b(Crashlytics.TAG, new StringBuilder("Crashlytics report upload ").append(r3z ? "complete: " : "FAILED: ").append(r8_z.b()).toString());
                if (r3z) {
                    r8_z.a();
                    r0z = true;
                }
            } catch (Exception e) {
                v.a().b().a(Crashlytics.TAG, new StringBuilder("Error occurred sending report ").append(r8_z).toString(), e);
            }
        }
        return r0z;
    }
}