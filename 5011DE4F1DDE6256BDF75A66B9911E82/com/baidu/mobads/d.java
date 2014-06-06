package com.baidu.mobads;

class d implements SplashAdListener {
    final /* synthetic */ SplashAd a;

    d(SplashAd r1_SplashAd) {
        this.a = r1_SplashAd;
    }

    public void onAdDismissed() {
        com.baidu.mobads.a.d.a("SplashAdListener.onAdDismissed");
    }

    public void onAdFailed(String r2_String) {
        com.baidu.mobads.a.d.a("SplashAdListener.reason");
    }

    public void onAdPresent() {
        com.baidu.mobads.a.d.a("SplashAdListener.onAdPresent");
    }
}