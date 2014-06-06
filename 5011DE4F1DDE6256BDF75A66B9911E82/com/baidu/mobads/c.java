package com.baidu.mobads;

import com.baidu.mobads.a.d;

class c implements InterstitialAdListener {
    final /* synthetic */ InterstitialAd a;

    c(InterstitialAd r1_InterstitialAd) {
        this.a = r1_InterstitialAd;
    }

    public void onAdClick(InterstitialAd r2_InterstitialAd) {
        d.a("InterstitialAdListener.onAdClick");
    }

    public void onAdDismissed() {
        d.a("InterstitialAdListener.onAdDismissed");
    }

    public void onAdFailed(String r2_String) {
        d.a("InterstitialAdListener.reason");
    }

    public void onAdPresent() {
        d.a("InterstitialAdListener.onAdPresent");
    }

    public void onAdReady() {
        d.a("InterstitialAdListener.onAdReady");
    }
}