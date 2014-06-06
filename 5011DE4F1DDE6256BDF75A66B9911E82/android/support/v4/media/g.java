package android.support.v4.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

// compiled from: TransportMediatorJellybeanMR2.java
class g extends BroadcastReceiver {
    final /* synthetic */ d a;

    g(d r1_d) {
        this.a = r1_d;
    }

    public void onReceive(Context r3_Context, Intent r4_Intent) {
        try {
            this.a.d.handleKey((KeyEvent) r4_Intent.getParcelableExtra("android.intent.extra.KEY_EVENT"));
        } catch (ClassCastException e) {
            Log.w("TransportController", e);
        }
    }
}