package com.qq.e.splash;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public abstract class SplashAdView extends RelativeLayout {
    public SplashAdView(Context r1_Context, String r2_String, String r3_String) {
        super(r1_Context);
    }

    public abstract void fetchAndShowIn(ViewGroup r1_ViewGroup);

    public abstract SplashAdListener getAdListener();

    public abstract void setAdListener(SplashAdListener r1_SplashAdListener);
}