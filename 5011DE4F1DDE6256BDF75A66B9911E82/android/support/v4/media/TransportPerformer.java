package android.support.v4.media;

import android.os.SystemClock;
import android.view.KeyEvent;
import com.tencent.mm.sdk.contact.RContact;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.widget.ProfileHeaderListView;

public abstract class TransportPerformer {
    public void onAudioFocusChange(int r9i) {
        int r6i = 0;
        switch (r9i) {
            case ProfileHeaderListView.INVALID_TAB_ID:
                r6i = RContact.MM_CONTACTFLAG_ALL;
                break;
        }
        if (r6i != 0) {
            long r1j = SystemClock.uptimeMillis();
            onMediaButtonDown(r6i, new KeyEvent(r1j, r1j, 0, r6i, 0));
            onMediaButtonUp(r6i, new KeyEvent(r1j, r1j, 1, r6i, 0));
        }
    }

    public int onGetBufferPercentage() {
        return 100;
    }

    public abstract long onGetCurrentPosition();

    public abstract long onGetDuration();

    public int onGetTransportControlFlags() {
        return NearbySelectView.TIME_60MIN;
    }

    public abstract boolean onIsPlaying();

    public boolean onMediaButtonDown(int r3i, KeyEvent r4_KeyEvent) {
        switch (r3i) {
            case 79:
            case 85:
                if (onIsPlaying()) {
                    onPause();
                } else {
                    onStart();
                }
                break;
            case 86:
                onStop();
                break;
            case ChatEngine.POST_CONV_FAIL:
                onStart();
                break;
            case RContact.MM_CONTACTFLAG_ALL:
                onPause();
                break;
        }
        return true;
    }

    public boolean onMediaButtonUp(int r2i, KeyEvent r3_KeyEvent) {
        return true;
    }

    public abstract void onPause();

    public abstract void onSeekTo(long r1j);

    public abstract void onStart();

    public abstract void onStop();
}