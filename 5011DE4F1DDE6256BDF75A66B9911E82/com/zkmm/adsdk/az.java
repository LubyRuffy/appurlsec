package com.zkmm.adsdk;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import qsbk.app.message.api.ChatEngine;

// compiled from: SourceFile
final class az extends Handler {
    private final /* synthetic */ Context a;

    az(Context r1_Context) {
        this.a = r1_Context;
    }

    public final void handleMessage(Message r5_Message) {
        try {
            m.d(this.a);
            m.R.sendEmptyMessageDelayed(0, ChatEngine.mQueryUnreadInterval);
        } catch (Exception e) {
        }
    }
}