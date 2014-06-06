package com.zkmm.adsdk;

// compiled from: SourceFile
final class be implements Runnable {
    final /* synthetic */ bd a;

    be(bd r1_bd) {
        this.a = r1_bd;
    }

    public final void run() {
        String r0_String = s.a(this.a.a.b, this.a.a.a, new StringBuilder(String.valueOf(this.a.a.d)).toString(), new String(m.b), m.h);
        if (this.a.a.c != null) {
            try {
                this.a.a.c.post(new bf(this, r0_String));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}