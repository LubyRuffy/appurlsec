package com.tencent.plus;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

// compiled from: ProGuard
class j implements OnGlobalLayoutListener {
    final /* synthetic */ ImageActivity a;

    j(ImageActivity r1_ImageActivity) {
        this.a = r1_ImageActivity;
    }

    public void onGlobalLayout() {
        this.a.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        ImageActivity.a(this.a, ImageActivity.a(this.a).a());
        ImageActivity.c(this.a).a(ImageActivity.b(this.a));
    }
}