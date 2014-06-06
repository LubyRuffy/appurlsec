package com.qq.e.v2.plugininterfaces;

import android.app.Activity;
import com.qq.e.ads.InterstitialAdListener;

public interface InterstitialAdViewInterface {
    public void closePopupWindow();

    public void loadAd();

    public void setAdListener(InterstitialAdListener r1_InterstitialAdListener);

    public void show();

    public void show(Activity r1_Activity);

    public void showAsPopupWindown();

    public void showAsPopupWindown(Activity r1_Activity);
}