package com.tencent.mm.sdk.platformtools;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class MTimerHandler extends Handler {
    private static int a;
    private final int b;
    private final boolean c;
    private long d;
    private final CallBack e;

    public static interface CallBack {
        public boolean onTimerExpired();
    }

    public MTimerHandler(Looper r3_Looper, CallBack r4_CallBack, boolean r5z) {
        super(r3_Looper);
        this.d = 0;
        this.e = r4_CallBack;
        this.b = a();
        this.c = r5z;
    }

    public MTimerHandler(CallBack r3_CallBack, boolean r4z) {
        this.d = 0;
        this.e = r3_CallBack;
        this.b = a();
        this.c = r4z;
    }

    private static int a() {
        if (a >= 8192) {
            a = 0;
        }
        int r0i = a + 1;
        a = r0i;
        return r0i;
    }

    protected void finalize() {
        stopTimer();
        super.finalize();
    }

    public void handleMessage(Message r4_Message) {
        if (r4_Message.what == this.b && this.e != null && this.e.onTimerExpired() && this.c) {
            sendEmptyMessageDelayed(this.b, this.d);
        }
    }

    public void startTimer(long r2j) {
        this.d = r2j;
        stopTimer();
        sendEmptyMessageDelayed(this.b, r2j);
    }

    public void stopTimer() {
        removeMessages(this.b);
    }

    public boolean stopped() {
        return !hasMessages(this.b);
    }
}