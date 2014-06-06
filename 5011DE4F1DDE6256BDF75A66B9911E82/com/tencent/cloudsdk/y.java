package com.tencent.cloudsdk;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

// compiled from: SourceFile
public class y extends ag implements Callback {
    private z a;
    private FileWriter b;
    private File c;
    private char[] d;
    private volatile ae e;
    private volatile ae f;
    private volatile ae g;
    private volatile ae h;
    private volatile boolean i;
    private HandlerThread j;
    private Handler k;

    public y(int r4i, boolean r5z, af r6_af, z r7_z) {
        super(r4i, r5z, r6_af);
        this.i = false;
        a(r7_z);
        this.e = new ae();
        this.f = new ae();
        this.g = this.e;
        this.h = this.f;
        this.d = new char[r7_z.f()];
        r7_z.b();
        h();
        this.j = new HandlerThread(r7_z.c(), r7_z.i());
        if (this.j != null) {
            this.j.start();
        }
        if (this.j.isAlive()) {
            this.k = new Handler(this.j.getLooper(), this);
        }
        f();
    }

    public y(z r4_z) {
        this(63, true, af.a, r4_z);
    }

    private void f() {
        this.k.sendEmptyMessageDelayed(Util.BYTE_OF_KB, c().g());
    }

    private void g() {
        if (Thread.currentThread() == this.j && (!this.i)) {
            this.i = true;
            j();
            try {
                this.h.a(h(), this.d);
                this.h.b();
            } catch (IOException e) {
                this.h.b();
            }
            this.i = false;
        }
    }

    private Writer h() {
        File r0_File = c().a();
        if (r0_File == null || r0_File.equals(this.c)) {
            return this.b;
        }
        this.c = r0_File;
        i();
        try {
            this.b = new FileWriter(this.c, true);
        } catch (IOException e) {
            return null;
        }
        return this.b;
    }

    private void i() {
        try {
            if (this.b != null) {
                this.b.flush();
                this.b.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void j() {
        synchronized (this) {
            if (this.g == this.e) {
                this.g = this.f;
                this.h = this.e;
            } else {
                this.g = this.e;
                this.h = this.f;
            }
        }
    }

    public void a() {
        if (this.k.hasMessages(Util.BYTE_OF_KB)) {
            this.k.removeMessages(Util.BYTE_OF_KB);
        }
        this.k.sendEmptyMessage(Util.BYTE_OF_KB);
    }

    protected void a(int r9i, Thread r10_Thread, long r11j, String r13_String, String r14_String, Throwable r15_Throwable) {
        a(e().a(r9i, r10_Thread, r11j, r13_String, r14_String, r15_Throwable));
    }

    public void a(z r1_z) {
        this.a = r1_z;
    }

    protected void a(String r3_String) {
        this.g.a(r3_String);
        if (this.g.a() >= c().f()) {
            a();
        }
    }

    public void b() {
        i();
        this.j.quit();
    }

    public z c() {
        return this.a;
    }

    public boolean handleMessage(Message r2_Message) {
        switch (r2_Message.what) {
            case Util.BYTE_OF_KB:
                g();
                f();
                break;
        }
        return true;
    }
}