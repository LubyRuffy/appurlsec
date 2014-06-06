package com.baidu.mobads;

public interface SplashAdListener {
    public void onAdDismissed();

    public void onAdFailed(String r1_String);

    public void onAdPresent();
}