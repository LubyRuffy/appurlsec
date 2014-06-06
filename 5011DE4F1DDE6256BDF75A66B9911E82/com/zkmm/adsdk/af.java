package com.zkmm.adsdk;

import android.os.Handler;
import android.os.Message;
import qsbk.app.share.ShareUtils;

// compiled from: SourceFile
final class af extends Handler {
    private /* synthetic */ AdDisplayer a;

    af(AdDisplayer r1_AdDisplayer) {
        this.a = r1_AdDisplayer;
    }

    public final void handleMessage(Message r6_Message) {
        super.handleMessage(r6_Message);
        switch (r6_Message.what) {
            case ShareUtils.SHARE_COPY:
                if (this.a.g != null) {
                    this.a.h.loadUrl(new StringBuilder("javascript:adwoFetchAudioPower(").append(((double) this.a.g.c()) / 32767.0d).append(");").toString());
                    this.a.s.sendEmptyMessageDelayed(ShareUtils.SHARE_COPY, (long) this.a.r);
                }
                break;
        }
    }
}