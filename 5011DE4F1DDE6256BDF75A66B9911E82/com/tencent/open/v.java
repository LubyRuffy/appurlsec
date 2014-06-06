package com.tencent.open;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

// compiled from: ProGuard
class v extends Handler {
    final /* synthetic */ AsynLoadImg a;

    v(AsynLoadImg r1_AsynLoadImg, Looper r2_Looper) {
        this.a = r1_AsynLoadImg;
        super(r2_Looper);
    }

    public void handleMessage(Message r4_Message) {
        Log.v("AsynLoadImg", "handleMessage:" + r4_Message.arg1);
        if (r4_Message.arg1 == 0) {
            this.a.d.saved(r4_Message.arg1, (String) r4_Message.obj);
        } else {
            this.a.d.saved(r4_Message.arg1, null);
        }
    }
}