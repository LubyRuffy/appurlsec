package com.zkmm.adsdk;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

// compiled from: SourceFile
final class cm implements OnCompletionListener {
    cm(cl r1_cl) {
    }

    public final void onCompletion(MediaPlayer r1_MediaPlayer) {
        if (r1_MediaPlayer != null) {
            r1_MediaPlayer.stop();
            r1_MediaPlayer.release();
        }
    }
}