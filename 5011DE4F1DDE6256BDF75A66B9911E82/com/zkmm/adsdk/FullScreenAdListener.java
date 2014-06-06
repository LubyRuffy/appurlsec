package com.zkmm.adsdk;

// compiled from: SourceFile
public interface FullScreenAdListener {
    public void onAdDismiss();

    public void onFailedToReceiveAd(ErrorCode r1_ErrorCode);

    public void onLoadAdComplete();

    public void onReceiveAd();
}