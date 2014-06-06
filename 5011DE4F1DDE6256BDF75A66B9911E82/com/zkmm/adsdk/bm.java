package com.zkmm.adsdk;

import android.widget.PopupWindow.OnDismissListener;
import qsbk.app.share.ShareUtils;

// compiled from: SourceFile
final class bm implements OnDismissListener {
    private /* synthetic */ bj a;

    bm(bj r1_bj) {
        this.a = r1_bj;
    }

    public final void onDismiss() {
        this.a.c.loadData("<html><body></body></html>", "text/html", null);
        this.a.c.clearHistory();
        this.a.c.clearView();
        bj.a(this.a);
        if (bj.o != null) {
            bj.o.onAdDismiss();
            bj.o = null;
        }
        cs.a = false;
        if (this.a.n) {
            s.a(new StringBuilder(String.valueOf(cs.c)).append("=").append(cs.d).toString(), new StringBuilder(String.valueOf(System.currentTimeMillis() - cs.b)).toString(), bj.j);
            s.a(cs.d, new StringBuilder(String.valueOf(m.J)).append("=").append(m.K).append("=").append(cs.c).toString(), bj.j);
        }
        this.a.m.removeMessages(ShareUtils.SHARE_COPY);
    }
}