package com.qq.e.feeds;

import android.app.Activity;
import android.content.Context;
import com.qq.e.comm.a;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;

public class FeedsAD {
    public static final int ERROR_INTERNAL = 1;
    public static final int ERROR_LOAD_AD_FAILED = 2;
    public static final int ERROR_PERMISSION_UNGRATIFIED = 3;
    private FeedsADDelegate a;
    private FeedsSetting b;
    private FeedsADListener c;

    public static interface FeedsADListener {
        public void onFeedsADFail(int r1i);

        public void onFeedsADLoaded(FeedsADViewRef r1_FeedsADViewRef);

        public void onFeedsADReady(FeedsADViewRef r1_FeedsADViewRef);
    }

    public FeedsAD(Activity r3_Activity, String r4_String, String r5_String, FeedsADListener r6_FeedsADListener) {
        if (StringUtil.isEmpty(r4_String) || StringUtil.isEmpty(r5_String)) {
            GDTLogger.e("AppId and positionID can not be empty while init FeedsAD");
        } else if (r3_Activity == null) {
            GDTLogger.e("Activity can not be null while init FeedsAD");
        } else if (r6_FeedsADListener == null) {
            GDTLogger.e("Listener should not be null while init FeedsAD");
        } else if (a.a((Context)r3_Activity)) {
            this.c = r6_FeedsADListener;
            if (GDTADManager.getInstance().initWith(r3_Activity.getApplicationContext(), r4_String)) {
                try {
                    this.a = GDTADManager.getInstance().getPM().getFeedsAdViewFactory().createFeedsADDelegate(r3_Activity, r4_String, r5_String, r6_FeedsADListener);
                } catch (com.qq.e.v2.managers.plugin.a e) {
                    GDTLogger.e("Fail to init FeedsAD", e);
                }
            } else {
                GDTLogger.e("GDTADManager init fail while init FeedsAD");
            }
        } else {
            GDTLogger.e("Manifest configuration error,check release document or demo application for help");
        }
    }

    public FeedsADViewRef getADViewRef() {
        if (this.a != null) {
            return this.a.getADViewRef();
        }
        GDTLogger.e("FeedsAD not initialized ,see logs");
        return null;
    }

    public FeedsSetting getSetting() {
        if (this.b == null) {
            this.b = new FeedsSetting();
        }
        return this.b;
    }

    public void loadAD() {
        if (this.a != null) {
            this.a.loadAD(getSetting());
        } else {
            if (this.c != null) {
                this.c.onFeedsADFail(ERROR_INTERNAL);
            }
            GDTLogger.e("FeedsAD not initialized ,see logs");
        }
    }

    public void setSetting(FeedsSetting r1_FeedsSetting) {
        this.b = r1_FeedsSetting;
    }
}