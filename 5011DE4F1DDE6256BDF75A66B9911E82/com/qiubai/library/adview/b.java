package com.qiubai.library.adview;

import android.widget.ImageView;

// compiled from: AdViewLayout.java
class b implements Runnable {
    final /* synthetic */ AdViewLayout a;
    private final /* synthetic */ ImageView b;

    b(AdViewLayout r1_AdViewLayout, ImageView r2_ImageView) {
        this.a = r1_AdViewLayout;
        this.b = r2_ImageView;
    }

    public void run() {
        this.b.setVisibility(0);
    }
}