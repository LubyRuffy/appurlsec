package com.tencent.cloudsdk;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.net.InetAddress;

// compiled from: SourceFile
class by {
    public long a;
    public InetAddress[] b;
    public String c;
    final /* synthetic */ cb d;

    private by(cb r3_cb) {
        this.d = r3_cb;
        this.a = 0;
        this.b = null;
        this.c = RContactStorage.PRIMARY_KEY;
    }

    public boolean a() {
        boolean r0z;
        r0z = (System.currentTimeMillis() > this.a ? 1 : (System.currentTimeMillis() == this.a? 0 : -1)) < 0;
        return r0z ? this.c.equals(em.d()) : r0z;
    }
}