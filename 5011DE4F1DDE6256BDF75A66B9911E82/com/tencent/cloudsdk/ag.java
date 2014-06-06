package com.tencent.cloudsdk;

// compiled from: SourceFile
public abstract class ag {
    private volatile int a;
    private volatile boolean b;
    private af c;

    public ag() {
        this(63, true, af.a);
    }

    public ag(int r2i, boolean r3z, af r4_af) {
        this.a = 63;
        this.b = true;
        this.c = af.a;
        a(r2i);
        a(r3z);
        a(r4_af);
    }

    public void a(int r1i) {
        this.a = r1i;
    }

    protected abstract void a(int r1i, Thread r2_Thread, long r3j, String r5_String, String r6_String, Throwable r7_Throwable);

    public void a(af r1_af) {
        this.c = r1_af;
    }

    public void a(boolean r1z) {
        this.b = r1z;
    }

    public void b(int r2i, Thread r3_Thread, long r4j, String r6_String, String r7_String, Throwable r8_Throwable) {
        if (d() && ap.a(this.a, r2i)) {
            a(r2i, r3_Thread, r4j, r6_String, r7_String, r8_Throwable);
        }
    }

    public boolean d() {
        return this.b;
    }

    public af e() {
        return this.c;
    }
}