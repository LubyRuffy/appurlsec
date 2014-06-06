package android.support.v4.media;

import android.view.KeyEvent;
import android.view.KeyEvent.Callback;

// compiled from: TransportMediator.java
class b implements Callback {
    final /* synthetic */ TransportMediator a;

    b(TransportMediator r1_TransportMediator) {
        this.a = r1_TransportMediator;
    }

    public boolean onKeyDown(int r2i, KeyEvent r3_KeyEvent) {
        return TransportMediator.a(r2i) ? this.a.b.onMediaButtonDown(r2i, r3_KeyEvent) : false;
    }

    public boolean onKeyLongPress(int r2i, KeyEvent r3_KeyEvent) {
        return false;
    }

    public boolean onKeyMultiple(int r2i, int r3i, KeyEvent r4_KeyEvent) {
        return false;
    }

    public boolean onKeyUp(int r2i, KeyEvent r3_KeyEvent) {
        return TransportMediator.a(r2i) ? this.a.b.onMediaButtonUp(r2i, r3_KeyEvent) : false;
    }
}