package com.qiubai.library.adview.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import com.baidu.mobads.AdService;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.qiubai.library.adview.AdViewAdRegistry;
import com.qiubai.library.adview.AdViewLayout;
import com.qiubai.library.adview.AdViewLayout.ViewAdRunnable;
import com.qiubai.library.adview.AdViewTargeting;
import com.qiubai.library.adview.AdViewTargeting.RunMode;
import com.qiubai.library.adview.obj.Ration;
import com.qiubai.library.adview.util.AdViewUtil;
import org.json.JSONObject;

public class AdBaiduAdapter extends AdViewAdapter implements AdViewListener {
    private boolean e;
    private AdView f;

    public AdBaiduAdapter() {
        this.e = false;
        this.f = null;
    }

    private static int a() {
        return AdViewUtil.NETWORK_TYPE_BAIDU;
    }

    public static void load(AdViewAdRegistry r2_AdViewAdRegistry) {
        try {
            if (Class.forName("com.baidu.mobads.AdView") != null) {
                r2_AdViewAdRegistry.registerClass(Integer.valueOf(a()), AdBaiduAdapter.class);
            }
        } catch (ClassNotFoundException e) {
        }
    }

    public void clean() {
        super.clean();
    }

    public void handle() {
        AdViewUtil.logInfo("Into Baidu");
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            Activity r1_Activity = (Activity) r0_AdViewLayout.activityReference.get();
            if (r1_Activity != null) {
                r0_AdViewLayout.removeAllViews();
                r0_AdViewLayout.activeRation = r0_AdViewLayout.nextRation;
                AdService r2_AdService = new AdService(r1_Activity, r0_AdViewLayout, new LayoutParams(-1, -2), this);
            }
        }
    }

    public void initAdapter(AdViewLayout r4_AdViewLayout, Ration r5_Ration) {
        Context r0_Context = (Context) r4_AdViewLayout.activityReference.get();
        if (AdViewTargeting.getRunMode() == RunMode.TEST) {
            AdView.setAppSid(r0_Context, "debug");
            AdView.setAppSec(r0_Context, "debug");
        } else {
            AdView.setAppSid(r0_Context, r5_Ration.key);
            AdView.setAppSec(r0_Context, r5_Ration.key2);
        }
    }

    public void onAdClick(JSONObject r2_JSONObject) {
        AdViewUtil.logInfo("onAdClick");
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            r0_AdViewLayout.reportClick();
        }
    }

    public void onAdFailed(String r3_String) {
        this.e = true;
        AdViewUtil.logInfo(new StringBuilder("AdViewListener.onAdFailed, reason=").append(r3_String).toString());
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            super.onFailed(r0_AdViewLayout, this.b);
        }
    }

    public void onAdReady(AdView r4_AdView) {
        AdViewUtil.logInfo("onAdReady");
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (!(r0_AdViewLayout == null || this.e)) {
            r0_AdViewLayout.adViewManager.resetRollover();
            r0_AdViewLayout.handler.post(new ViewAdRunnable(r0_AdViewLayout, r4_AdView));
            r0_AdViewLayout.rotateThreadedDelayed();
            this.e = false;
        }
    }

    public void onAdShow(JSONObject r2_JSONObject) {
        AdViewUtil.logInfo("onAdShow");
    }

    public void onAdSwitch() {
        AdViewUtil.logInfo("onAdSwitch");
    }

    public void onVideoClickAd() {
        AdViewUtil.logInfo("onAdClick");
        AdViewLayout r0_AdViewLayout = (AdViewLayout) this.a.get();
        if (r0_AdViewLayout == null) {
        } else {
            r0_AdViewLayout.reportClick();
        }
    }

    public void onVideoClickClose() {
    }

    public void onVideoClickReplay() {
    }

    public void onVideoError() {
    }

    public void onVideoFinish() {
    }

    public void onVideoStart() {
    }
}