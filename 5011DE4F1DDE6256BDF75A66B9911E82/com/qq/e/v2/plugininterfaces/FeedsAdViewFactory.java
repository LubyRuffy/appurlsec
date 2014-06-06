package com.qq.e.v2.plugininterfaces;

import android.app.Activity;
import com.qq.e.feeds.FeedsAD.FeedsADListener;
import com.qq.e.feeds.FeedsADDelegate;
import com.qq.e.feedsad.CustomFeedsViewBuilder;
import com.qq.e.feedsad.FeedsAD;
import com.qq.e.feedsad.FeedsADSetting;

public interface FeedsAdViewFactory {
    public FeedsADDelegate createFeedsADDelegate(Activity r1_Activity, String r2_String, String r3_String, FeedsADListener r4_FeedsADListener);

    public com.qq.e.feedsad.FeedsADDelegate createNewFeedsADDelegate(Activity r1_Activity, String r2_String, String r3_String, FeedsADSetting r4_FeedsADSetting, FeedsAD.FeedsADListener r5_FeedsAD_FeedsADListener, CustomFeedsViewBuilder r6_CustomFeedsViewBuilder);
}