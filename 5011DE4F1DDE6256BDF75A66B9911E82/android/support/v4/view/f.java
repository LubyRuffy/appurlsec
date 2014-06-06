package android.support.v4.view;

import android.view.KeyEvent;
import android.view.KeyEvent.Callback;
import android.view.KeyEvent.DispatcherState;
import android.view.View;

// compiled from: KeyEventCompatEclair.java
class f {
    public static boolean dispatch(KeyEvent r1_KeyEvent, Callback r2_Callback, Object r3_Object, Object r4_Object) {
        return r1_KeyEvent.dispatch(r2_Callback, (DispatcherState) r3_Object, r4_Object);
    }

    public static Object getKeyDispatcherState(View r1_View) {
        return r1_View.getKeyDispatcherState();
    }

    public static boolean isTracking(KeyEvent r1_KeyEvent) {
        return r1_KeyEvent.isTracking();
    }

    public static void startTracking(KeyEvent r0_KeyEvent) {
        r0_KeyEvent.startTracking();
    }
}