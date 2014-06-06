package com.tencent.cloudsdk.defaultsdk.mna.report;

import com.tencent.cloudsdk.cf;
import com.tencent.cloudsdk.ch;
import com.tencent.cloudsdk.cl;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.cloudsdk.cz;
import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import com.tencent.cloudsdk.df;
import com.tencent.cloudsdk.dn;
import com.tencent.cloudsdk.em;
import com.tencent.cloudsdk.en;
import java.net.MalformedURLException;
import java.net.URL;

// compiled from: SourceFile
public class HttpStatistics {
    public static final int ERR_CODE_FAIL = -1;
    public static final int ERR_CODE_SUCC = 0;
    private long a;
    private long b;

    private void a(String r10_String, int r11i, long r12j) {
        int r3i = 0;
        dn r0_dn = cz.c();
        try {
            String r1_String = new URL(r10_String).getHost();
            cl r2_cl = cf.a(GlobalContext.getContext()).a(r1_String, (int)(em.b() != 1 ? em.a() : (short) 0), true);
            if (r2_cl == null || r2_cl.f == null || r2_cl.f.size() == 0) {
            } else {
                ch r3_ch = (ch) r2_cl.f.get(r3i);
                r0_dn.a(r1_String, r3_ch.a(), r3_ch.b(), r11i, r12j, this.b);
            }
        } catch (MalformedURLException e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e("HttpStatistics", r0_Throwable.getMessage(), r0_Throwable);
        }
    }

    public void report(String r8_String, int r9i, long r10j) {
        en.a.post(new df(this, r8_String, r9i, r10j));
    }

    public void timeEnd() {
        this.b = System.currentTimeMillis() - this.a;
    }

    public void timeStart() {
        this.a = System.currentTimeMillis();
    }
}