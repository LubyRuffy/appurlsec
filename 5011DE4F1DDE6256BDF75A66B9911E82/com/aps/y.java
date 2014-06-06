package com.aps;

import android.os.Looper;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Timer;

final class y extends Thread {
    private /* synthetic */ ae a;

    y(ae r1_ae, String r2_String) {
        this.a = r1_ae;
        super(r2_String);
    }

    public final void run() {
        String r0_String = RContactStorage.PRIMARY_KEY;
        Looper.prepare();
        this.a.D = Looper.myLooper();
        this.a.A = new Timer();
        this.a.v = new ac((byte) 0);
        ae.a(this.a, this.a.v);
        if (this.a.B == null) {
            this.a.t = true;
            this.a.B = new z(this.a, r0_String);
            this.a.B.start();
        }
        this.a.w = new ad((byte) 0);
        ae.a(this.a, this.a.w);
        this.a.y = new af((byte) 0);
        this.a.a(this.a.y);
        this.a.z();
        Looper.loop();
    }
}