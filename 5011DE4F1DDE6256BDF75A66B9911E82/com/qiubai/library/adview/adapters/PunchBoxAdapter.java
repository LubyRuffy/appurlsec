package com.qiubai.library.adview.adapters;

import android.app.Activity;
import com.punchbox.PunchBox;
import com.punchbox.exception.PBException;
import com.punchbox.listener.AdListener;
import com.punchbox.request.FixedAdRequest;
import com.punchbox.view.FixedAdView;
import com.qiubai.library.adview.AdViewAdRegistry;
import com.qiubai.library.adview.AdViewLayout;
import com.qiubai.library.adview.AdViewTargeting;
import com.qiubai.library.adview.AdViewTargeting.RunMode;
import com.qiubai.library.adview.obj.Ration;
import com.qiubai.library.adview.util.AdViewUtil;

public class PunchBoxAdapter extends AdViewAdapter implements AdListener {
    private Activity e;
    private FixedAdView f;

    public PunchBoxAdapter() {
        this.f = null;
    }

    private static int a() {
        return AdViewUtil.NETWORK_TYPE_PUNCHBOX;
    }

    public static void load(AdViewAdRegistry r2_AdViewAdRegistry) {
        try {
            if (Class.forName("com.punchbox.listener.AdListener") != null) {
                r2_AdViewAdRegistry.registerClass(Integer.valueOf(a()), PunchBoxAdapter.class);
            }
        } catch (ClassNotFoundException e) {
        }
    }

    public void clean() {
        super.clean();
        if (this.f != null) {
            AdViewUtil.logInfo("release punchbox");
            this.f.destroy();
            this.f = null;
        }
    }

    public void handle() {
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            PunchBox r1_PunchBox = PunchBox.getInstance(this.e);
            FixedAdRequest r2_FixedAdRequest = new FixedAdRequest(this.e);
            this.f = new FixedAdView(this.e);
            if (AdViewTargeting.getRunMode() == RunMode.TEST) {
                r1_PunchBox.setAppID(this.e, "22222222-2222-2222-2222-222222222222");
            } else {
                r1_PunchBox.setAppID(this.e, this.b.key);
            }
            r1_PunchBox.setServerMode(false);
            this.f.setDisplayInterval(a.MAX_ACTIVITY_COUNT_UNLIMITED);
            this.f.setAdListener(this);
            this.f.loadAd(r2_FixedAdRequest);
            r0_AdViewLayout.AddSubView(this.f);
        }
    }

    public void initAdapter(AdViewLayout r2_AdViewLayout, Ration r3_Ration) {
        this.e = (Activity) r2_AdViewLayout.activityReference.get();
    }

    public void onDismissScreen() {
        AdViewUtil.logInfo("onDismissScreen");
    }

    public void onFailedToReceiveAd(PBException r3_PBException) {
        AdViewUtil.logInfo(new StringBuilder("AdViewListener.onAdFailed, reason=").append(r3_PBException).toString());
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            super.onFailed(r0_AdViewLayout, this.b);
        }
    }

    public void onPresentScreen() {
        AdViewUtil.logInfo("onPresentScreen");
    }

    public void onReceiveAd() {
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
}