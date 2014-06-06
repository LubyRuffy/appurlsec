package com.tencent.cloudsdk;

import android.content.SharedPreferences;
import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;

// compiled from: SourceFile
class bw implements Runnable {
    private String a;
    private int b;
    private int c;
    private int d;
    private SharedPreferences e;

    public bw(String r1_String, int r2i, int r3i, int r4i) {
        this.a = r1_String;
        this.b = r2i;
        this.c = r3i;
        this.d = r4i;
    }

    public void run() {
        short r5s = (short) 0;
        if (GlobalContext.getContext() == null || em.b() == -1) {
        } else {
            this.e = GlobalContext.getContext().getSharedPreferences("ansetting", 0);
            if (this.e.getInt("ver", 0) != this.d) {
                er.a("AnsSetting", new StringBuilder(">>>\u7248\u672c\u53f7\u6709\u53d8\u66f4\uff0c\u5f00\u59cb\u62c9\u53d6\uff1a[lastVersion\uff1a").append(this.e.getInt("ver", r5s)).append("][currentVersion:").append(this.d).append("][").append(Thread.currentThread().getId()).append("]").toString());
                new bd(this.a, this.b, this.c, new bf((short) 5, (short) 3, r5s, (short) 1).a(), new bx(this)).run();
            }
        }
    }
}