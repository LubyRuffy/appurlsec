package com.qiubai.library.adview.adapters;

import android.app.Activity;
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.qiubai.library.adview.AdViewAdRegistry;
import com.qiubai.library.adview.AdViewLayout;
import com.qiubai.library.adview.AdViewLayout.ViewAdRunnable;
import com.qiubai.library.adview.obj.Ration;
import com.qiubai.library.adview.util.AdViewUtil;

public class AdMobAdapter extends AdViewAdapter implements AdListener {
    private AdView e;

    private static int a() {
        return 1;
    }

    public static void load(AdViewAdRegistry r2_AdViewAdRegistry) {
        try {
            if (Class.forName("com.google.ads.AdView") != null) {
                r2_AdViewAdRegistry.registerClass(Integer.valueOf(a()), AdMobAdapter.class);
            }
        } catch (ClassNotFoundException e) {
        }
    }

    protected AdRequest a(AdViewLayout r2_AdViewLayout) {
        return new AdRequest();
    }

    public void clean() {
        super.clean();
        if (this.e != null) {
            this.e.setAdListener(null);
            this.e.destroy();
        }
        this.e = null;
        AdViewUtil.logInfo("release AdMob");
    }

    public void handle() {
        AdViewUtil.logInfo("Into AdMob");
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            Activity r1_Activity = (Activity) r0_AdViewLayout.activityReference.get();
            if (r1_Activity != null) {
                this.e = new AdView(r1_Activity, AdSize.SMART_BANNER, this.b.key);
                this.e.setAdListener(this);
                this.e.loadAd(a(r0_AdViewLayout));
            }
        }
    }

    public void initAdapter(AdViewLayout r1_AdViewLayout, Ration r2_Ration) {
    }

    public void onDismissScreen(Ad r2_Ad) {
        AdViewUtil.logInfo("AdMob onDismissScreen");
    }

    public void onFailedToReceiveAd(Ad r3_Ad, ErrorCode r4_ErrorCode) {
        AdViewUtil.logInfo("AdMob fail");
        r3_Ad.setAdListener(null);
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            super.onFailed(r0_AdViewLayout, this.b);
            r0_AdViewLayout.rotateThreadedPri(1);
        }
    }

    public void onLeaveApplication(Ad r2_Ad) {
        AdViewUtil.logInfo("AdMob onLeaveApplication");
    }

    public void onPresentScreen(Ad r2_Ad) {
        AdViewUtil.logInfo("AdMob onPresentScreen");
        if (((AdViewLayout) this.a.get()) == null) {
        }
    }

    public void onReceiveAd(Ad r5_Ad) {
        AdViewUtil.logInfo("AdMob success");
        r5_Ad.setAdListener(null);
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout != null && r5_Ad instanceof AdView) {
            super.onSuccessed(r0_AdViewLayout, this.b);
            this.e = (AdView) r5_Ad;
            r0_AdViewLayout.adViewManager.resetRollover();
            r0_AdViewLayout.handler.post(new ViewAdRunnable(r0_AdViewLayout, this.e));
            r0_AdViewLayout.rotateThreadedDelayed();
        }
    }
}