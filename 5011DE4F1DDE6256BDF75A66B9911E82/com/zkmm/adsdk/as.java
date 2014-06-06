package com.zkmm.adsdk;

import android.os.Handler;
import android.os.Message;

// compiled from: SourceFile
final class as extends Handler {
    private /* synthetic */ ZKMMAdView a;

    as(ZKMMAdView r1_ZKMMAdView) {
        this.a = r1_ZKMMAdView;
    }

    public final void handleMessage(Message r4_Message) {
        if (this.a.hasWindowFocus()) {
            boolean r0z = false;
            if (this.a.a == null || this.a.a.a() == null) {
                if (cs.a || r0z) {
                } else {
                    this.a.f();
                }
            } else {
                r0z = this.a.a.a().j;
                if (cs.a || r0z) {
                } else {
                    this.a.f();
                }
            }
        }
        if (!this.a.e) {
            sendEmptyMessageDelayed(100, (long) (bg.a * 1000));
        }
    }
}