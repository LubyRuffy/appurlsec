package android.support.v4.media;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.support.v4.view.KeyEventCompat;
import android.view.KeyEvent;
import android.view.KeyEvent.Callback;
import android.view.View;
import java.util.ArrayList;

public class TransportMediator extends TransportController {
    public static final int FLAG_KEY_MEDIA_FAST_FORWARD = 64;
    public static final int FLAG_KEY_MEDIA_NEXT = 128;
    public static final int FLAG_KEY_MEDIA_PAUSE = 16;
    public static final int FLAG_KEY_MEDIA_PLAY = 4;
    public static final int FLAG_KEY_MEDIA_PLAY_PAUSE = 8;
    public static final int FLAG_KEY_MEDIA_PREVIOUS = 1;
    public static final int FLAG_KEY_MEDIA_REWIND = 2;
    public static final int FLAG_KEY_MEDIA_STOP = 32;
    public static final int KEYCODE_MEDIA_PAUSE = 127;
    public static final int KEYCODE_MEDIA_PLAY = 126;
    public static final int KEYCODE_MEDIA_RECORD = 130;
    final Context a;
    final TransportPerformer b;
    final AudioManager c;
    final View d;
    final Object e;
    final d f;
    final ArrayList<TransportStateListener> g;
    final c h;
    final Callback i;

    public TransportMediator(Activity r2_Activity, TransportPerformer r3_TransportPerformer) {
        this(r2_Activity, null, r3_TransportPerformer);
    }

    private TransportMediator(Activity r6_Activity, View r7_View, TransportPerformer r8_TransportPerformer) {
        this.g = new ArrayList();
        this.h = new a(this);
        this.i = new b(this);
        this.a = r6_Activity != null ? r6_Activity : r7_View.getContext();
        this.b = r8_TransportPerformer;
        this.c = (AudioManager) this.a.getSystemService("audio");
        if (r6_Activity != null) {
            r7_View = r6_Activity.getWindow().getDecorView();
        }
        this.d = r7_View;
        this.e = KeyEventCompat.getKeyDispatcherState(this.d);
        if (VERSION.SDK_INT >= 18) {
            this.f = new d(this.a, this.c, this.d, this.h);
        } else {
            this.f = null;
        }
    }

    public TransportMediator(View r2_View, TransportPerformer r3_TransportPerformer) {
        this(null, r2_View, r3_TransportPerformer);
    }

    static boolean a(int r1i) {
        switch (r1i) {
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case KEYCODE_MEDIA_PLAY:
            case KEYCODE_MEDIA_PAUSE:
            case KEYCODE_MEDIA_RECORD:
                return true;
        }
        return false;
    }

    private TransportStateListener[] a() {
        if (this.g.size() <= 0) {
            return null;
        }
        TransportStateListener[] r0_TransportStateListenerA = new TransportStateListener[this.g.size()];
        this.g.toArray(r0_TransportStateListenerA);
        return r0_TransportStateListenerA;
    }

    private void b() {
        TransportStateListener[] r1_TransportStateListenerA = a();
        if (r1_TransportStateListenerA != null) {
            int r2i = r1_TransportStateListenerA.length;
            int r0i = 0;
            while (r0i < r2i) {
                r1_TransportStateListenerA[r0i].onPlayingChanged(this);
                r0i++;
            }
        }
    }

    private void c() {
        TransportStateListener[] r1_TransportStateListenerA = a();
        if (r1_TransportStateListenerA != null) {
            int r2i = r1_TransportStateListenerA.length;
            int r0i = 0;
            while (r0i < r2i) {
                r1_TransportStateListenerA[r0i].onTransportControlsChanged(this);
                r0i++;
            }
        }
    }

    private void d() {
        if (this.f != null) {
            this.f.refreshState(this.b.onIsPlaying(), this.b.onGetCurrentPosition(), this.b.onGetTransportControlFlags());
        }
    }

    public void destroy() {
        this.f.destroy();
    }

    public boolean dispatchKeyEvent(KeyEvent r3_KeyEvent) {
        return KeyEventCompat.dispatch(r3_KeyEvent, this.i, this.e, this);
    }

    public int getBufferPercentage() {
        return this.b.onGetBufferPercentage();
    }

    public long getCurrentPosition() {
        return this.b.onGetCurrentPosition();
    }

    public long getDuration() {
        return this.b.onGetDuration();
    }

    public Object getRemoteControlClient() {
        return this.f != null ? this.f.getRemoteControlClient() : null;
    }

    public int getTransportControlFlags() {
        return this.b.onGetTransportControlFlags();
    }

    public boolean isPlaying() {
        return this.b.onIsPlaying();
    }

    public void pausePlaying() {
        if (this.f != null) {
            this.f.pausePlaying();
        }
        this.b.onPause();
        d();
        b();
    }

    public void refreshState() {
        d();
        b();
        c();
    }

    public void registerStateListener(TransportStateListener r2_TransportStateListener) {
        this.g.add(r2_TransportStateListener);
    }

    public void seekTo(long r2j) {
        this.b.onSeekTo(r2j);
    }

    public void startPlaying() {
        if (this.f != null) {
            this.f.startPlaying();
        }
        this.b.onStart();
        d();
        b();
    }

    public void stopPlaying() {
        if (this.f != null) {
            this.f.stopPlaying();
        }
        this.b.onStop();
        d();
        b();
    }

    public void unregisterStateListener(TransportStateListener r2_TransportStateListener) {
        this.g.remove(r2_TransportStateListener);
    }
}