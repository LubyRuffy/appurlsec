package com.tencent.mm.sdk.storage;

class d implements Runnable {
    final /* synthetic */ Object a;
    final /* synthetic */ Object b;
    final /* synthetic */ MStorageEvent c;

    d(MStorageEvent r1_MStorageEvent, Object r2_Object, Object r3_Object) {
        this.c = r1_MStorageEvent;
        this.a = r2_Object;
        this.b = r3_Object;
    }

    public void run() {
        this.c.a(this.a, this.b);
    }
}