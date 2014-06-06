package com.tencent.cloudsdk.defaultsdk.mna.report;

import com.tencent.cloudsdk.cf;
import com.tencent.cloudsdk.ch;
import com.tencent.cloudsdk.cl;
import com.tencent.cloudsdk.cz;
import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import com.tencent.cloudsdk.dg;
import com.tencent.cloudsdk.em;
import com.tencent.cloudsdk.en;
import com.tencent.cloudsdk.ep;

// compiled from: SourceFile
public class TSocketRecvStatistics {
    public static final int ERR_CODE_FAIL = -1;
    public static final int ERR_CODE_SUCC = 0;
    private long a;
    private long b;

    private void a(String r9_String, int r10i, long r11j) {
        boolean r1z = false;
        cl r0_cl = cf.a(GlobalContext.getContext()).a(r9_String, (int)(em.b() != 1 ? em.a() : (short) 0), false);
        if (r0_cl == null || r0_cl.f == null || r0_cl.f.size() == 0) {
        } else {
            ch r1_ch = (ch) r0_cl.f.get(r1z);
            cz.b().b(r9_String, r1_ch.a(), r1_ch.b(), r10i, r11j, (int) this.b);
        }
    }

    public void report(String r8_String, int r9i, long r10j) {
        if (r8_String == null || ep.b(r8_String) || r10j < 1048576) {
        } else {
            en.a.post(new dg(this, r8_String, r9i, r10j));
        }
    }

    public void timeEnd() {
        this.b = System.currentTimeMillis() - this.a;
    }

    public void timeStart() {
        this.a = System.currentTimeMillis();
    }
}