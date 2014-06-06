package com.zkmm.adsdk;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

// compiled from: SourceFile
final class ao implements OnErrorListener {
    private final /* synthetic */ Dialog a;

    ao(aD r1_aD, Dialog r2_Dialog) {
        this.a = r2_Dialog;
    }

    public final boolean onError(MediaPlayer r2_MediaPlayer, int r3i, int r4i) {
        if (this.a != null) {
            this.a.dismiss();
        }
        return true;
    }
}