package com.tencent.mm.sdk.platformtools;

import com.tencent.mm.sdk.platformtools.MTimerHandler.CallBack;

class e implements CallBack {
    final /* synthetic */ LBSManager a;

    e(LBSManager r1_LBSManager) {
        this.a = r1_LBSManager;
    }

    public boolean onTimerExpired() {
        Log.v("MicroMsg.LBSManager", "get location by GPS failed.");
        this.a.a = true;
        this.a.start();
        this.a.j = false;
        return false;
    }
}