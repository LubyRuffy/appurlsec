package com.baidu.mobstat;

import android.content.Context;
import android.os.Process;
import android.support.v4.util.TimeUtils;
import com.baidu.mobstat.a.c;

class j extends Thread {
    private static j a;
    private Context b;
    private boolean c;
    private boolean d;

    static {
        a = new j();
    }

    private j() {
        this.c = false;
        this.d = false;
    }

    public static j a() {
        return a;
    }

    private void d() {
        this.c = true;
    }

    private synchronized void e() {
        this.d = true;
    }

    public void a(Context r2_Context) {
        if (r2_Context == null || b()) {
        } else {
            this.b = r2_Context;
            d();
            start();
            c.a("**************load caceh**start********");
        }
    }

    public boolean b() {
        return this.c;
    }

    public synchronized boolean c() {
        return this.d;
    }

    public void run() {
        Process.setThreadPriority(TimeUtils.HUNDRED_DAY_FIELD_LEN);
        while (!(this.d)) {
            k.a().a(this.b);
            DataCore.getInstance().loadStatData(this.b);
            DataCore.getInstance().loadLastSession(this.b);
            e();
            synchronized (a) {
                try {
                    notifyAll();
                } catch (IllegalMonitorStateException e) {
                    c.a("stat", e);
                }
            }
            DataCore.getInstance().installHeader(this.b);
            k.a().b(this.b);
            c.a("**************load caceh**end********");
        }
    }
}