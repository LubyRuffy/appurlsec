package com.zkmm.adsdk;

import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.util.Log;

// compiled from: SourceFile
final class bc implements OnErrorListener {
    private /* synthetic */ ae a;

    bc(ae r1_ae) {
        this.a = r1_ae;
    }

    public final void onError(MediaRecorder r4_MediaRecorder, int r5i, int r6i) {
        Log.i("MediaRecorder", new StringBuilder("setOnErrorListener ").append(r5i).toString());
        this.a.b();
    }
}