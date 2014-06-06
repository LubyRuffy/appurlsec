package com.baidu.mobads;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import com.baidu.mobads.a.d;
import qsbk.app.push.Utils;

class b implements Callback {
    private final InterstitialAdListener a;
    private final InterstitialAd b;

    public b(InterstitialAdListener r1_InterstitialAdListener, InterstitialAd r2_InterstitialAd) {
        this.a = r1_InterstitialAdListener;
        this.b = r2_InterstitialAd;
    }

    public boolean handleMessage(Message r7_Message) {
        try {
            Bundle r0_Bundle = r7_Message.getData();
            String r1_String = r0_Bundle.getString(Utils.RESPONSE_METHOD);
            Object[] r2_ObjectA = new Object[2];
            r2_ObjectA[0] = "AdView.setInterstitialListener handleMessage";
            r2_ObjectA[1] = r0_Bundle;
            d.a(r2_ObjectA);
            if ("onInterstitialPreloadEnd".equals(r1_String)) {
                this.b.setAdReady(true);
                this.a.onAdReady();
                return false;
            } else if ("onAdDismissed".equals(r1_String)) {
                this.b.removeAd();
                this.a.onAdDismissed();
                return false;
            } else if ("onAdClick".equals(r1_String)) {
                this.a.onAdClick(this.b);
                return false;
            } else if ("onAdFailed".equals(r1_String)) {
                this.a.onAdFailed(r0_Bundle.getString("p_reason"));
                return false;
            } else {
                if ("onInterstitialAdPresent".equals(r1_String)) {
                    this.a.onAdPresent();
                }
                return false;
            }
        } catch (Exception e) {
            d.b(e);
        }
    }
}