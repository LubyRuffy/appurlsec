package com.google.analytics.tracking.android;

import android.os.Handler.Callback;
import android.os.Message;

// compiled from: GAServiceManager.java
class k implements Callback {
    final /* synthetic */ GAServiceManager a;

    k(GAServiceManager r1_GAServiceManager) {
        this.a = r1_GAServiceManager;
    }

    public boolean handleMessage(Message r6_Message) {
        if (1 != r6_Message.what || !GAServiceManager.a.equals(r6_Message.obj)) {
            return true;
        }
        this.a.dispatch();
        if (this.a.e <= 0 || this.a.i) {
            return true;
        }
        this.a.h.sendMessageDelayed(this.a.h.obtainMessage(1, GAServiceManager.a), (long) (this.a.e * 1000));
        return true;
    }
}