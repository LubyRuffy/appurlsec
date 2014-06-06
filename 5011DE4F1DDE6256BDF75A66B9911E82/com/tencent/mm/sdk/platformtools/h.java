package com.tencent.mm.sdk.platformtools;

import android.telephony.PhoneStateListener;

class h extends PhoneStateListener {
    final /* synthetic */ g a;

    h(g r1_g) {
        this.a = r1_g;
    }

    public void onSignalStrengthChanged(int r4i) {
        super.onSignalStrengthChanged(r4i);
        g.a(r4i * 2 - 113);
        if (g.a(this.a) != null) {
            g.a(this.a).listen(g.b(this.a), 0);
        }
    }
}