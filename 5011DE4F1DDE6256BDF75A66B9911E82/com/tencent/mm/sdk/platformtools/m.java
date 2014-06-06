package com.tencent.mm.sdk.platformtools;

class m implements Runnable {
    final /* synthetic */ SyncTask a;

    m(SyncTask r1_SyncTask) {
        this.a = r1_SyncTask;
    }

    public void run() {
        this.a.e = Util.ticksToNow(this.a.d);
        this.a.setResult(this.a.a());
    }
}