package com.qiubai.library.adview;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: AdViewLayout.java
class a implements OnClickListener {
    final /* synthetic */ AdViewLayout a;

    a(AdViewLayout r1_AdViewLayout) {
        this.a = r1_AdViewLayout;
    }

    public void onClick(View r3_View) {
        if (this.a.adViewInterface != null) {
            this.a.adViewInterface.onClosedAd();
            AdViewLayout.b(this.a, true);
        }
    }
}