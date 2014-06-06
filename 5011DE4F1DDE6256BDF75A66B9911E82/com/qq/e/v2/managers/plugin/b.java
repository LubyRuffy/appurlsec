package com.qq.e.v2.managers.plugin;

import com.qq.e.splash.AdViewFactory;
import com.qq.e.v2.plugininterfaces.ActivityDelegateFactory;
import com.qq.e.v2.plugininterfaces.AppWallViewFactory;
import com.qq.e.v2.plugininterfaces.BannerViewFactory;
import com.qq.e.v2.plugininterfaces.FeedsAdViewFactory;
import com.qq.e.v2.plugininterfaces.GridAdViewFactory;
import com.qq.e.v2.plugininterfaces.InterstitialViewFactory;
import com.qq.e.v2.plugininterfaces.ServiceDelegateFactory;
import java.util.HashMap;

final class b extends HashMap<Class<?>, String> {
    b() {
        put(ServiceDelegateFactory.class, "com.qq.e.v2.plugin.ServiceDelegateFactoryImpl");
        put(ActivityDelegateFactory.class, "com.qq.e.v2.plugin.ActivityDelegateFactoryImpl");
        put(BannerViewFactory.class, "com.qq.e.v2.plugin.BannerViewFactoryImpl");
        put(AdViewFactory.class, "com.qq.e.v2.plugin.AdViewFactoryImpl");
        put(GridAdViewFactory.class, "com.qq.e.v2.plugin.GridAdViewFactoryImpl");
        put(FeedsAdViewFactory.class, "com.qq.e.v2.plugin.FeedsAdViewFactoryImpl");
        put(InterstitialViewFactory.class, "com.qq.e.v2.plugin.InterstitialViewFactoryImpl");
        put(AppWallViewFactory.class, "com.qq.e.v2.plugin.AppWallViewFactoryImpl");
    }
}