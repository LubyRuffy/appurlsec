package com.qiubai.library.adview.adapters;

import android.app.Activity;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.baidu.location.BDLocation;
import com.mobisage.android.MobiSageAdBanner;
import com.mobisage.android.MobiSageAdBannerListener;
import com.mobisage.android.MobiSageManager;
import com.qiubai.library.adview.AdViewAdRegistry;
import com.qiubai.library.adview.AdViewLayout;
import com.qiubai.library.adview.obj.Ration;
import com.qiubai.library.adview.util.AdViewUtil;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

public class MobiSageAdapter extends AdViewAdapter implements MobiSageAdBannerListener {
    private MobiSageAdBanner e;

    private static int a() {
        return AdViewUtil.NETWORK_TYPE_ADSAGE;
    }

    public static void load(AdViewAdRegistry r2_AdViewAdRegistry) {
        try {
            if (Class.forName("com.mobisage.android.MobiSageAdBannerListener") != null) {
                r2_AdViewAdRegistry.registerClass(Integer.valueOf(a()), MobiSageAdapter.class);
            }
        } catch (ClassNotFoundException e) {
        }
    }

    public void clean() {
        super.clean();
        if (this.e != null) {
            this.e.destoryAdView();
            this.e = null;
        }
    }

    public void handle() {
        AdViewUtil.logInfo("Into MobiSage");
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            Activity r1_Activity = (Activity) r0_AdViewLayout.activityReference.get();
            if (r1_Activity != null) {
                MobiSageManager.getInstance().setPublisherID(this.b.key);
                this.e = new MobiSageAdBanner(r1_Activity);
                this.e.setAdRefreshInterval(Integer.valueOf(0));
                this.e.setAnimeType(BDLocation.TypeOffLineLocationNetworkFail);
                this.e.setMobiSageAdBannerListener(this);
                r0_AdViewLayout.activeRation = r0_AdViewLayout.nextRation;
                r0_AdViewLayout.removeAllViews();
                LayoutParams r1_LayoutParams = new RelativeLayout.LayoutParams(-2, -2);
                r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER);
                r0_AdViewLayout.addView(this.e, r1_LayoutParams);
                r0_AdViewLayout.addCloseButton(r0_AdViewLayout);
            }
        }
    }

    public void initAdapter(AdViewLayout r1_AdViewLayout, Ration r2_Ration) {
    }

    public void onMobiSageAdViewUpdate(Object r2_Object) {
        AdViewUtil.logInfo("onMobiSageAdViewUpdate");
    }

    public void onMobiSageBannerClick(MobiSageAdBanner r2_MobiSageAdBanner) {
        AdViewUtil.logInfo("onMobiSageAdViewClick");
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            r0_AdViewLayout.reportClick();
        }
    }

    public void onMobiSageBannerClose(MobiSageAdBanner r1_MobiSageAdBanner) {
    }

    public void onMobiSageBannerError(MobiSageAdBanner r3_MobiSageAdBanner) {
        AdViewUtil.logInfo("onMobiSageAdViewError");
        this.e.setMobiSageAdBannerListener(null);
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            super.onFailed(r0_AdViewLayout, this.b);
        }
    }

    public void onMobiSageBannerHide(MobiSageAdBanner r1_MobiSageAdBanner) {
    }

    public void onMobiSageBannerHideWindow(MobiSageAdBanner r1_MobiSageAdBanner) {
    }

    public void onMobiSageBannerPopupWindow(MobiSageAdBanner r1_MobiSageAdBanner) {
    }

    public void onMobiSageBannerShow(MobiSageAdBanner r4_MobiSageAdBanner) {
        AdViewUtil.logInfo("onMobiSageAdViewShow");
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            super.onSuccessed(r0_AdViewLayout, this.b);
            this.e.setMobiSageAdBannerListener(null);
            r0_AdViewLayout.adViewManager.resetRollover();
            r0_AdViewLayout.rotateThreadedDelayed();
            r0_AdViewLayout.reportImpression();
        }
    }
}