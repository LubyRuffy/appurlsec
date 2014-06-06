package android.support.v4.view;

import android.view.KeyEvent;

// compiled from: KeyEventCompatHoneycomb.java
class g {
    public static boolean metaStateHasModifiers(int r1i, int r2i) {
        return KeyEvent.metaStateHasModifiers(r1i, r2i);
    }

    public static boolean metaStateHasNoModifiers(int r1i) {
        return KeyEvent.metaStateHasNoModifiers(r1i);
    }

    public static int normalizeMetaState(int r1i) {
        return KeyEvent.normalizeMetaState(r1i);
    }
}