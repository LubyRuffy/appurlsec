package android.support.v4.view;

import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.KeyEvent;
import android.view.KeyEvent.Callback;
import android.view.View;
import com.tencent.mm.sdk.contact.RContact;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewHeader;

public class KeyEventCompat {
    static final d a;

    static interface d {
        public boolean dispatch(KeyEvent r1_KeyEvent, Callback r2_Callback, Object r3_Object, Object r4_Object);

        public Object getKeyDispatcherState(View r1_View);

        public boolean isTracking(KeyEvent r1_KeyEvent);

        public boolean metaStateHasModifiers(int r1i, int r2i);

        public boolean metaStateHasNoModifiers(int r1i);

        public int normalizeMetaState(int r1i);

        public void startTracking(KeyEvent r1_KeyEvent);
    }

    static class a implements d {
        a() {
        }

        private static int a_(int r5i, int r6i, int r7i, int r8i, int r9i) {
            int r2i;
            int r0i = 1;
            r2i = (r6i & r7i) != 0 ? 1 : 0;
            int r3i = r8i | r9i;
            if ((r6i & r3i) != 0) {
                if (r2i == 0) {
                    if (r0i != 0) {
                        return r5i & (r3i ^ -1);
                    }
                    throw new IllegalArgumentException("bad arguments");
                } else if (r0i == 0) {
                }
            } else {
                r0i = 0;
                if (r2i == 0) {
                    return r0i == 0 ? r5i : r5i & (r7i ^ -1);
                } else {
                    if (r0i != 0) {
                        return r5i & (r3i ^ -1);
                    }
                    throw new IllegalArgumentException("bad arguments");
                }
            }
        }

        public boolean dispatch(KeyEvent r2_KeyEvent, Callback r3_Callback, Object r4_Object, Object r5_Object) {
            return r2_KeyEvent.dispatch(r3_Callback);
        }

        public Object getKeyDispatcherState(View r2_View) {
            return null;
        }

        public boolean isTracking(KeyEvent r2_KeyEvent) {
            return false;
        }

        public boolean metaStateHasModifiers(int r6i, int r7i) {
            return a(a(normalizeMetaState(r6i) & 247, r7i, 1, RContact.MM_CONTACTFLAG_FAVOURCONTACT, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS), r7i, XListViewHeader.STATE_REFRESHING, Base64.URL_SAFE, Base64.ORDERED) == r7i;
        }

        public boolean metaStateHasNoModifiers(int r2i) {
            return (normalizeMetaState(r2i) & 247) == 0;
        }

        public int normalizeMetaState(int r3i) {
            int r0i;
            r0i = (r3i & 192) != 0 ? r3i | 1 : r3i;
            if ((r0i & 48) != 0) {
                r0i |= 2;
            }
            return r0i & 247;
        }

        public void startTracking(KeyEvent r1_KeyEvent) {
        }
    }

    static class b extends a {
        b() {
        }

        public boolean dispatch(KeyEvent r2_KeyEvent, Callback r3_Callback, Object r4_Object, Object r5_Object) {
            return f.dispatch(r2_KeyEvent, r3_Callback, r4_Object, r5_Object);
        }

        public Object getKeyDispatcherState(View r2_View) {
            return f.getKeyDispatcherState(r2_View);
        }

        public boolean isTracking(KeyEvent r2_KeyEvent) {
            return f.isTracking(r2_KeyEvent);
        }

        public void startTracking(KeyEvent r1_KeyEvent) {
            f.startTracking(r1_KeyEvent);
        }
    }

    static class c extends b {
        c() {
        }

        public boolean metaStateHasModifiers(int r2i, int r3i) {
            return g.metaStateHasModifiers(r2i, r3i);
        }

        public boolean metaStateHasNoModifiers(int r2i) {
            return g.metaStateHasNoModifiers(r2i);
        }

        public int normalizeMetaState(int r2i) {
            return g.normalizeMetaState(r2i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            a = new c();
        } else {
            a = new a();
        }
    }

    public static boolean dispatch(KeyEvent r1_KeyEvent, Callback r2_Callback, Object r3_Object, Object r4_Object) {
        return a.dispatch(r1_KeyEvent, r2_Callback, r3_Object, r4_Object);
    }

    public static Object getKeyDispatcherState(View r1_View) {
        return a.getKeyDispatcherState(r1_View);
    }

    public static boolean hasModifiers(KeyEvent r2_KeyEvent, int r3i) {
        return a.metaStateHasModifiers(r2_KeyEvent.getMetaState(), r3i);
    }

    public static boolean hasNoModifiers(KeyEvent r2_KeyEvent) {
        return a.metaStateHasNoModifiers(r2_KeyEvent.getMetaState());
    }

    public static boolean isTracking(KeyEvent r1_KeyEvent) {
        return a.isTracking(r1_KeyEvent);
    }

    public static boolean metaStateHasModifiers(int r1i, int r2i) {
        return a.metaStateHasModifiers(r1i, r2i);
    }

    public static boolean metaStateHasNoModifiers(int r1i) {
        return a.metaStateHasNoModifiers(r1i);
    }

    public static int normalizeMetaState(int r1i) {
        return a.normalizeMetaState(r1i);
    }

    public static void startTracking(KeyEvent r1_KeyEvent) {
        a.startTracking(r1_KeyEvent);
    }
}