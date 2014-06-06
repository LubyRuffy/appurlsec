package com.tencent.cloudsdk;

import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import java.util.Iterator;

// compiled from: SourceFile
class di implements Runnable {
    private di() {
    }

    public void run() {
        dl.c().set(false);
        dl r1_dl = new dl();
        Iterator r2_Iterator = cf.a(GlobalContext.getContext()).a().iterator();
        while (r2_Iterator.hasNext()) {
            r1_dl.a((cj) r2_Iterator.next());
        }
        dl.b();
    }
}