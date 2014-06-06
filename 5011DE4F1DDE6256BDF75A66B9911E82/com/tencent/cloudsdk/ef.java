package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import java.util.Iterator;

// compiled from: SourceFile
class ef implements Runnable {
    final /* synthetic */ dy a;

    private ef(dy r1_dy) {
        this.a = r1_dy;
    }

    public void run() {
        try {
            String r5_String = "0";
            this.a.a(this.a.n);
            if (this.a.f.size() == 0 && this.a.g.size() == 1) {
                eh.a().a(this.a.b);
            } else {
                Iterator r6_Iterator = this.a.j.keySet().iterator();
                while (r6_Iterator.hasNext()) {
                    cn r3_cn = (cn) r6_Iterator.next();
                    new Thread(new ee(this.a, r3_cn.d, r3_cn, (cn) this.a.j.get(r3_cn), r5_String)).start();
                }
                r6_Iterator = this.a.g.iterator();
                while (r6_Iterator.hasNext()) {
                    cn r4_cn = (cn) r6_Iterator.next();
                    new Thread(new ee(this.a, r4_cn.d, null, r4_cn, r5_String)).start();
                }
                if (this.a.j.size() + this.a.g.size() == 0) {
                    eh.a().a(this.a.b);
                }
            }
        } catch (eq e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e(dy.a, r0_Throwable.getMessage(), r0_Throwable);
            eh.a().a(this.a.b);
        }
    }
}