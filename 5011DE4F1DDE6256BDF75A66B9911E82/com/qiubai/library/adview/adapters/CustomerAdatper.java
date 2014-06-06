package com.qiubai.library.adview.adapters;

import android.app.Activity;
import com.qiubai.library.adview.AdViewAdRegistry;
import com.qiubai.library.adview.AdViewLayout;
import com.qiubai.library.adview.AdViewTargeting;
import com.qiubai.library.adview.AdViewTargeting.RunMode;
import com.qiubai.library.adview.obj.Ration;
import com.qiubai.library.adview.util.AdViewUtil;
import com.qq.e.ads.AdListener;
import com.qq.e.ads.AdRequest;
import com.qq.e.ads.AdSize;
import com.qq.e.ads.AdView;

public class CustomerAdatper extends AdViewAdapter implements AdListener {
    private Activity e;
    private AdView f;

    public CustomerAdatper() {
        this.f = null;
    }

    private static int a() {
        return AdViewUtil.NETWORK_TYPE_CUSTOMIZE;
    }

    public static void load(AdViewAdRegistry r2_AdViewAdRegistry) {
        try {
            if (Class.forName("com.qq.e.ads.AdListener") != null) {
                r2_AdViewAdRegistry.registerClass(Integer.valueOf(a()), CustomerAdatper.class);
            }
        } catch (ClassNotFoundException e) {
        }
    }

    public void clean() {
        this.f.destroyDrawingCache();
        this.f = null;
        super.clean();
    }

    public void handle() {
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            Activity r1_Activity = (Activity) r0_AdViewLayout.activityReference.get();
            if (r1_Activity != null) {
                AdViewUtil.logInfo(new StringBuilder("ration.key\uff1a").append(this.b.key).append(",ration.key2\uff1a").append(this.b.key2).toString());
                this.f = new AdView(r1_Activity, AdSize.BANNER, this.b.key, this.b.key2);
                this.f.setAdListener(this);
                AdRequest r1_AdRequest = new AdRequest();
                if (AdViewTargeting.getRunMode() == RunMode.TEST) {
                    r1_AdRequest.setTestAd(true);
                } else {
                    r1_AdRequest.setTestAd(false);
                }
                r1_AdRequest.setRefresh(0);
                this.f.fetchAd(r1_AdRequest);
                r0_AdViewLayout.AddSubView(this.f);
            }
        }
    }

    public void initAdapter(AdViewLayout r2_AdViewLayout, Ration r3_Ration) {
        this.e = (Activity) r2_AdViewLayout.activityReference.get();
    }

    public void onAdReceiv() {
        AdViewUtil.logInfo("onReceiveAd");
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            super.onSuccessed(r0_AdViewLayout, this.b);
            r0_AdViewLayout.adViewManager.resetRollover();
            r0_AdViewLayout.rotateThreadedDelayed();
            r0_AdViewLayout.reportImpression();
        }
    }

    public void onNoAd() {
        AdViewUtil.logInfo("AdViewListener.onAdFailed");
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            super.onFailed(r0_AdViewLayout, this.b);
            r0_AdViewLayout.rotateThreadedPri(1);
        }
    }
}