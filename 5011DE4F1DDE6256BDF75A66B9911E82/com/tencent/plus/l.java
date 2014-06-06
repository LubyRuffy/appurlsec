package com.tencent.plus;

import qsbk.app.utils.Base64;

// compiled from: ProGuard
class l implements Runnable {
    final /* synthetic */ h a;

    l(h r1_h) {
        this.a = r1_h;
    }

    public void run() {
        ImageActivity.e(this.a.a).setEnabled(true);
        ImageActivity.e(this.a.a).setTextColor(-1);
        ImageActivity.f(this.a.a).setEnabled(true);
        ImageActivity.f(this.a.a).setTextColor(-1);
        ImageActivity.f(this.a.a).setText("\u91cd\u8bd5");
        ImageActivity.d(this.a.a).setVisibility(Base64.DONT_BREAK_LINES);
        ImageActivity.a(this.a.a, true);
    }
}