package com.zkmm.adsdk;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SourceFile
final class bs implements OnClickListener {
    private /* synthetic */ bj a;

    bs(bj r1_bj) {
        this.a = r1_bj;
    }

    public final void onClick(View r2_View) {
        this.a.c.reload();
    }
}