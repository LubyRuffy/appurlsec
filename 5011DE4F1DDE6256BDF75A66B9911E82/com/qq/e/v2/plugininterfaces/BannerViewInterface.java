package com.qq.e.v2.plugininterfaces;

import android.view.View;
import com.qq.e.ads.AdListener;
import com.qq.e.ads.AdRequest;

public interface BannerViewInterface {
    public void destroy();

    public void fetchAd(AdRequest r1_AdRequest);

    public View getView();

    public void setAdListener(AdListener r1_AdListener);
}