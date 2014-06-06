package com.qq.e.feedsad;

import android.app.Activity;
import android.content.Context;
import com.qq.e.comm.a;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;
import java.util.Collection;

public class FeedsAD {
    public static final int ERROR_INTERNAL = 1;
    public static final int ERROR_LOAD_AD_FAILED = 2;
    public static final int ERROR_PERMISSION_UNGRATIFIED = 3;
    private FeedsADDelegate a;
    private FeedsADSetting b;
    private FeedsADListener c;

    public static interface FeedsADListener {
        public void onFeedsADFail(int r1i);

        public void onFeedsADLoaded(Collection<FeedsADViewRef> r1_Collection_FeedsADViewRef);

        public void onFeedsADReady(FeedsADViewRef r1_FeedsADViewRef);
    }

    public FeedsAD(Activity r8_Activity, String r9_String, String r10_String, FeedsADSetting r11_FeedsADSetting, FeedsADListener r12_FeedsADListener) {
        this(r8_Activity, r9_String, r10_String, r11_FeedsADSetting, r12_FeedsADListener, null);
    }

    public FeedsAD(Activity r8_Activity, String r9_String, String r10_String, FeedsADSetting r11_FeedsADSetting, FeedsADListener r12_FeedsADListener, CustomFeedsViewBuilder r13_CustomFeedsViewBuilder) {
        if (StringUtil.isEmpty(r9_String) || StringUtil.isEmpty(r10_String)) {
            GDTLogger.e("AppId and positionID can not be empty while init FeedsAD");
        } else if (r8_Activity == null) {
            GDTLogger.e("Activity can not be null while init FeedsAD");
        } else if (r12_FeedsADListener == null) {
            GDTLogger.e("Listener should not be null while init FeedsAD");
        } else if (a.a((Context)r8_Activity)) {
            this.c = r12_FeedsADListener;
            if (GDTADManager.getInstance().initWith(r8_Activity.getApplicationContext(), r9_String)) {
                try {
                    this.a = GDTADManager.getInstance().getPM().getFeedsAdViewFactory().createNewFeedsADDelegate(r8_Activity, r9_String, r10_String, r11_FeedsADSetting, r12_FeedsADListener, r13_CustomFeedsViewBuilder);
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

    public FeedsADSetting getSetting() {
        if (this.b == null) {
            this.b = new FeedsADSetting();
        }
        return this.b;
    }

    public void loadAD(int r3i) {
        if (this.a != null) {
            this.a.loadAD(r3i);
        } else {
            if (this.c != null) {
                this.c.onFeedsADFail(ERROR_INTERNAL);
            }
            GDTLogger.e("FeedsAD not initialized ,see logs");
        }
    }
}