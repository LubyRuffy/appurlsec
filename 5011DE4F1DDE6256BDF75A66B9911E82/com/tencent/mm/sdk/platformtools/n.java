package com.tencent.mm.sdk.platformtools;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

final class n implements OnCompletionListener {
    n() {
    }

    public final void onCompletion(MediaPlayer r1_MediaPlayer) {
        r1_MediaPlayer.release();
    }
}