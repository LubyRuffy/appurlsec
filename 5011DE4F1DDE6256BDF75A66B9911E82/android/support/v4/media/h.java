package android.support.v4.media;

import android.media.AudioManager.OnAudioFocusChangeListener;

// compiled from: TransportMediatorJellybeanMR2.java
class h implements OnAudioFocusChangeListener {
    final /* synthetic */ d a;

    h(d r1_d) {
        this.a = r1_d;
    }

    public void onAudioFocusChange(int r2i) {
        this.a.d.handleAudioFocusChange(r2i);
    }
}