package com.qiubai.library.adview.adapters;

import android.app.Activity;
import android.view.ViewGroup;
import com.qiubai.library.adview.AdViewAdRegistry;
import com.qiubai.library.adview.AdViewLayout;
import com.qiubai.library.adview.AdViewTargeting;
import com.qiubai.library.adview.AdViewTargeting.RunMode;
import com.qiubai.library.adview.obj.Ration;
import com.qiubai.library.adview.util.AdViewUtil;
import com.zkmm.adsdk.AdListener;
import com.zkmm.adsdk.ErrorCode;
import com.zkmm.adsdk.ZKMMAdView;

public class AdwoAdapter extends AdViewAdapter implements AdListener {
    private static int a() {
        return AdViewUtil.NETWORK_TYPE_ADWO;
    }

    public static void load(AdViewAdRegistry r2_AdViewAdRegistry) {
        try {
            if (Class.forName("com.zkmm.adsdk.ZKMMAdView") != null) {
                r2_AdViewAdRegistry.registerClass(Integer.valueOf(a()), AdwoAdapter.class);
            }
        } catch (ClassNotFoundException e) {
        }
    }

    public void handle() {
        AdViewUtil.logInfo("Into Adwo");
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (!(r0_AdViewLayout == null || ((Activity) r0_AdViewLayout.activityReference.get()) == null)) {
            ViewGroup r1_ViewGroup;
            ZKMMAdView.setBannerMatchScreenWidth(true);
            r1_ViewGroup = AdViewTargeting.getRunMode() == RunMode.TEST ? new ZKMMAdView(r0_AdViewLayout.getContext(), this.b.key, true, 0) : new ZKMMAdView(r0_AdViewLayout.getContext(), this.b.key, false, 0);
            r1_ViewGroup.setAnimationType(0);
            r1_ViewGroup.setListener(this);
            r0_AdViewLayout.AddSubView(r1_ViewGroup);
        }
    }

    public void initAdapter(AdViewLayout r1_AdViewLayout, Ration r2_Ration) {
    }

    public void onDismissScreen() {
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout != null) {
            r0_AdViewLayout.setHasWindow(true);
        }
    }

    public void onFailedToReceiveAd(ZKMMAdView r3_ZKMMAdView, ErrorCode r4_ErrorCode) {
        AdViewUtil.logInfo(new StringBuilder("onFailedToReceiveAd, ecode=").append(r4_ErrorCode).toString());
        r3_ZKMMAdView.setListener(null);
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            super.onFailed(r0_AdViewLayout, this.b);
        }
    }

    public void onPresentScreen() {
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout != null) {
            r0_AdViewLayout.setHasWindow(false);
        }
    }

    public void onReceiveAd(ZKMMAdView r3_ZKMMAdView) {
        AdViewUtil.logInfo("onReceiveAd");
        r3_ZKMMAdView.setListener(null);
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            super.onSuccessed(r0_AdViewLayout, this.b);
            r0_AdViewLayout.reportImpression();
            r0_AdViewLayout.adViewManager.resetRollover();
            r0_AdViewLayout.rotateThreadedDelayed();
        }
    }
}