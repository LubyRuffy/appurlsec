package com.zkmm.adsdk;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

// compiled from: SourceFile
final class f implements OnCompletionListener {
    private /* synthetic */ E a;

    f(E r1_E) {
        this.a = r1_E;
    }

    public final void onCompletion(MediaPlayer r3_MediaPlayer) {
        cs.g(E.a(this.a));
        cs.a = false;
        if (E.a(this.a) != null) {
            E.a(this.a).loadUrl("javascript:adwoDoPlayVideoComplete();");
        }
    }
}