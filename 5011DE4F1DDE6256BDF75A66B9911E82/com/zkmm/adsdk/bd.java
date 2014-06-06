package com.zkmm.adsdk;

import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;

// compiled from: SourceFile
final class bd implements OnInfoListener {
    private /* synthetic */ ae a;

    bd(ae r1_ae) {
        this.a = r1_ae;
    }

    public final void onInfo(MediaRecorder r4_MediaRecorder, int r5i, int r6i) {
        if (r5i == 800 || r5i == 801 || r5i == 1) {
            if (this.a.a != null) {
                new Thread(new be(this)).start();
                this.a.b();
            } else {
                if (this.a.c != null) {
                    this.a.c.loadUrl(new StringBuilder("javascript:adwoVoiceRecordComplete(").append(null).append(");").toString());
                }
                this.a.b();
            }
        } else {
            this.a.b();
        }
    }
}