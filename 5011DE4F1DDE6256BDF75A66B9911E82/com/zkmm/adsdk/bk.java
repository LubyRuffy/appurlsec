package com.zkmm.adsdk;

import android.os.Handler;
import android.os.Message;
import qsbk.app.share.ShareUtils;

// compiled from: SourceFile
final class bk extends Handler {
    private /* synthetic */ bj a;

    bk(bj r1_bj) {
        this.a = r1_bj;
    }

    public final void handleMessage(Message r6_Message) {
        super.handleMessage(r6_Message);
        switch (r6_Message.what) {
            case ShareUtils.SHARE_COPY:
                if (this.a.b != null) {
                    this.a.c.loadUrl(new StringBuilder("javascript:adwoFetchAudioPower(").append(((double) this.a.b.c()) / 32767.0d).append(");").toString());
                    this.a.m.sendEmptyMessageDelayed(ShareUtils.SHARE_COPY, (long) this.a.l);
                }
                break;
        }
    }
}