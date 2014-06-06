package com.qq.e.v2.plugininterfaces;

import android.app.Activity;
import com.qq.e.ads.AdSize;

public interface BannerViewFactory {
    public BannerViewInterface getBannerView(Activity r1_Activity, AdSize r2_AdSize, String r3_String, String r4_String, boolean r5z);
}