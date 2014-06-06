package com.qq.e.splash;

import android.app.Activity;
import android.view.ViewGroup;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.managers.plugin.a;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;

public final class SplashAd {
    public static final int ERROR_LOAD_AD_FAILED = 1;
    public static final int ERROR_REJECT_LOAD_AD = 2;
    public static final int INTERNAL_ERROR = -1;
    private SplashAdView a;

    public SplashAd(Activity r3_Activity, ViewGroup r4_ViewGroup, String r5_String, String r6_String, SplashAdListener r7_SplashAdListener) {
        if (StringUtil.isEmpty(r5_String) || StringUtil.isEmpty(r6_String) || r4_ViewGroup == null) {
            GDTLogger.e("APPID and PosId should not be null or empty string;and splash container should not be null");
            a(r7_SplashAdListener);
        } else {
            try {
                if (GDTADManager.getInstance().initWith(r3_Activity, r5_String)) {
                    this.a = GDTADManager.getInstance().getPM().getAdViewFactory().createSplashAdView(r3_Activity, r5_String, r6_String);
                    if (this.a != null) {
                        this.a.setAdListener(r7_SplashAdListener);
                        this.a.fetchAndShowIn(r4_ViewGroup);
                    } else {
                        GDTLogger.e("SplashAdView created by factory return null");
                        a(r7_SplashAdListener);
                    }
                } else {
                    GDTLogger.e("Fail to Init GDT AD SDK,report logcat info filter by gdt_ad_mob");
                    a(r7_SplashAdListener);
                }
            } catch (a e) {
                GDTLogger.report("Fail to init splash plugin", e);
                a(r7_SplashAdListener);
            } catch (Throwable th) {
                GDTLogger.report("Unknown Exception", th);
                a(r7_SplashAdListener);
            }
        }
    }

    private static void a(SplashAdListener r1_SplashAdListener) {
        if (r1_SplashAdListener != null) {
            r1_SplashAdListener.onAdFailed(INTERNAL_ERROR);
        }
    }
}