package com.zkmm.adsdk;

// compiled from: SourceFile
public interface AdListener {
    public void onDismissScreen();

    public void onFailedToReceiveAd(ZKMMAdView r1_ZKMMAdView, ErrorCode r2_ErrorCode);

    public void onPresentScreen();

    public void onReceiveAd(ZKMMAdView r1_ZKMMAdView);
}