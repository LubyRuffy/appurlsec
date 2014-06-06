package android.support.v4.media;

import android.view.KeyEvent;

// compiled from: TransportMediatorCallback.java
interface c {
    public long getPlaybackPosition();

    public void handleAudioFocusChange(int r1i);

    public void handleKey(KeyEvent r1_KeyEvent);

    public void playbackPositionUpdate(long r1j);
}