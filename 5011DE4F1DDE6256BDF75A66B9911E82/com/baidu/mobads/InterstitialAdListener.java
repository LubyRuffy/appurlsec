package com.baidu.mobads;

public interface InterstitialAdListener {
    public void onAdClick(InterstitialAd r1_InterstitialAd);

    public void onAdDismissed();

    public void onAdFailed(String r1_String);

    public void onAdPresent();

    public void onAdReady();
}