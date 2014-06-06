package com.tencent.cloudsdk;

import android.content.SharedPreferences.Editor;
import com.tencent.cloudsdk.tsocket.GlobalContext;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;

// compiled from: SourceFile
public class f {
    public static final String a;
    private static final byte[] b;
    private static f c;
    private int d;
    private long e;
    private boolean f;

    static {
        a = f.class.getSimpleName();
        b = new byte[0];
        c = null;
    }

    private f() {
        this.d = 1200;
        this.e = 0;
        this.f = false;
    }

    public static f a() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new f();
                }
            }
        }
        return c;
    }

    public String a(a r5_a) {
        return GlobalContext.getContext().getSharedPreferences("perf_config", 0).getString(new StringBuilder("path_").append(r5_a.a()).toString(), new StringBuilder(String.valueOf(b())).append(File.separator).append(r5_a.a()).append(".jar").toString());
    }

    public void a(int r1i) {
        this.d = r1i;
    }

    public void a(long r1j) {
        this.e = r1j;
    }

    public void a(a r4_a, String r5_String) {
        Editor r0_Editor = GlobalContext.getContext().getSharedPreferences("perf_config", 0).edit();
        r0_Editor.putString(new StringBuilder("path_").append(r4_a.a()).toString(), r5_String);
        r0_Editor.commit();
    }

    public String b() {
        String r0_String = GlobalContext.getContext().getFilesDir().getAbsolutePath();
        return new StringBuilder(String.valueOf(r0_String)).append(r0_String.endsWith(File.separator) ? RContactStorage.PRIMARY_KEY : File.separator).append(b.c).toString();
    }

    public boolean b(a r3_a) {
        File r1_File = new File(a(r3_a));
        return r1_File.exists() && r1_File.canRead();
    }

    public String c() {
        String r0_String = GlobalContext.getContext().getFilesDir().getAbsolutePath();
        return new StringBuilder(String.valueOf(r0_String)).append(r0_String.endsWith(File.separator) ? RContactStorage.PRIMARY_KEY : File.separator).append(b.d).toString();
    }

    public String d() {
        String r0_String = GlobalContext.getContext().getFilesDir().getAbsolutePath();
        return new StringBuilder(String.valueOf(r0_String)).append(r0_String.endsWith(File.separator) ? RContactStorage.PRIMARY_KEY : File.separator).append(b.e).append(File.separator).toString();
    }

    public int e() {
        return this.d;
    }

    public void f_() {
        Editor r2_Editor = GlobalContext.getContext().getSharedPreferences("perf_config", 0).edit();
        r2_Editor.putLong("pref_allow_check_point", this.e + ((long) (this.d * 1000)));
        r2_Editor.commit();
    }

    public long g() {
        return GlobalContext.getContext().getSharedPreferences("perf_config", 0).getLong("pref_allow_check_point", 0);
    }

    public boolean h() {
        return this.f;
    }
}