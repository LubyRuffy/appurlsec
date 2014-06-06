package com.aps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

final class af extends BroadcastReceiver {
    final /* synthetic */ ae a;

    private af(ae r1_ae) {
        this.a = r1_ae;
    }

    public final void onReceive(Context r6_Context, Intent r7_Intent) {
        if (r6_Context == null || r7_Intent == null || ae.f(this.a) == null || ae.g(this.a) == null || ae.h(this.a) == null || r7_Intent.getAction() == null || (!"android.net.wifi.SCAN_RESULTS".equals(r7_Intent.getAction()))) {
        } else {
            List r2_List = ae.f(this.a).getScanResults();
            ae.a(this.a, r2_List);
            synchronized (this) {
                ae.h(this.a).clear();
                ae.a(this.a, System.currentTimeMillis());
                if (r2_List == null || r2_List.size() <= 0) {
                } else {
                    int r1i = 0;
                    while (r1i < r2_List.size()) {
                        ae.h(this.a).add((ScanResult) r2_List.get(r1i));
                        r1i++;
                    }
                }
            }
            TimerTask r0_TimerTask = new ag(this);
            if (ae.g(this.a) != null) {
                ae.g(this.a).cancel();
                ae.a(this.a, null);
            }
            ae.a(this.a, new Timer());
            ae.g(this.a).schedule(r0_TimerTask, (long) ae.y());
        }
    }
}