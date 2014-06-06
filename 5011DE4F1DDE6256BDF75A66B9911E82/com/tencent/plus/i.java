package com.tencent.plus;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import com.qiubai.library.adview.util.AdViewUtil;

// compiled from: ProGuard
class i implements OnClickListener {
    final /* synthetic */ ImageActivity a;

    i(ImageActivity r1_ImageActivity) {
        this.a = r1_ImageActivity;
    }

    public void onClick(View r7_View) {
        ImageActivity.d(this.a).setVisibility(0);
        ImageActivity.e(this.a).setEnabled(false);
        ImageActivity.e(this.a).setTextColor(Color.rgb(AdViewUtil.NETWORK_TYPE_WOOBOO, AdViewUtil.NETWORK_TYPE_WOOBOO, AdViewUtil.NETWORK_TYPE_WOOBOO));
        ImageActivity.f(this.a).setEnabled(false);
        ImageActivity.f(this.a).setTextColor(Color.rgb(AdViewUtil.NETWORK_TYPE_APPMEDIA, 94, 134));
        new Thread(new k(this)).start();
        if (ImageActivity.h(this.a)) {
            this.a.a("10657", 0);
        } else {
            this.a.a("10655", System.currentTimeMillis() - ImageActivity.i(this.a));
            if (ImageActivity.c(this.a).b) {
                this.a.a("10654", 0);
            }
        }
    }
}