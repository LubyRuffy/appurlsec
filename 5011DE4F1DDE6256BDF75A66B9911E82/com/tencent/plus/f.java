package com.tencent.plus;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: ProGuard
class f implements OnClickListener {
    final /* synthetic */ ImageActivity a;

    f(ImageActivity r1_ImageActivity) {
        this.a = r1_ImageActivity;
    }

    public void onClick(View r5_View) {
        this.a.a("10656", System.currentTimeMillis() - ImageActivity.i(this.a));
        this.a.setResult(0);
        ImageActivity.j(this.a);
    }
}