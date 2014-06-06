package android.support.v4.media;

import android.view.KeyEvent;

// compiled from: TransportMediator.java
class a implements c {
    final /* synthetic */ TransportMediator a;

    a(TransportMediator r1_TransportMediator) {
        this.a = r1_TransportMediator;
    }

    public long getPlaybackPosition() {
        return this.a.b.onGetCurrentPosition();
    }

    public void handleAudioFocusChange(int r2i) {
        this.a.b.onAudioFocusChange(r2i);
    }

    public void handleKey(KeyEvent r2_KeyEvent) {
        r2_KeyEvent.dispatch(this.a.i);
    }

    public void playbackPositionUpdate(long r2j) {
        this.a.b.onSeekTo(r2j);
    }
}